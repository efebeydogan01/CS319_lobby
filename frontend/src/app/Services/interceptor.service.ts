// import {Injectable} from "@angular/core";
// import {HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
// import {LoginService} from "./login-service.service";
// import {exhaustMap, take} from "rxjs";
//
// @Injectable()
// export class InterceptorService implements HttpInterceptor {
//
//   constructor( private loginService: LoginService) {}
//
//   intercept( req: HttpRequest<any>, next: HttpHandler) {
//     this.loginService.user.pipe (
//       take(1),
//       exhaustMap( user => {
//         return next.handle( req);
//       })
//     );
//   }
// }
