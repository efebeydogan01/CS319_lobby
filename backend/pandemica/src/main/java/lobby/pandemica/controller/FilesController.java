package lobby.pandemica.controller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.db.FileInfo;
import lobby.pandemica.db.ResponseMessage;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.service.FilesStorageService;
import lobby.pandemica.service.base.BaseCrudService;
import lobby.pandemica.serviceimpl.FilesStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
//@CrossOrigin("http://localhost:8081")
@RequestMapping("file")
public class FilesController extends BaseController<CovidInformationDto> {

    static int count = 0;
    @Autowired
    FilesStorageService storageService;

    public FilesController(BaseCrudService<CovidInformationDto> baseCrudService, FilesStorageService storageService) {
        super(baseCrudService);
        this.storageService = storageService;
    }

    @PostMapping("/upload/{id}")
    public  ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String id) {
        FilesStorageServiceImpl filesStorageService = new FilesStorageServiceImpl();
        if( count == 0)
        {
            count++;
            storageService.deleteAll();
            storageService.init();
        }
        String message = "";
        try {
            storageService.save(file, id);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String id){
        Resource file = storageService.load(id + ".pdf");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    /*
    @GetMapping("/files/{filename:.+}/")
    @ResponseBody
    public ResponseEntity<String> getFileAsString(@PathVariable String filename) throws IOException {
        Resource file = storageService.load(filename);
        File myFile = file.getFile();
        //PDDocument document = PDDocument.load(myFile);
        String fileString = "";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(fileString);
    }
    */
}