import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Injectable} from "@angular/core";
import {map, Observable, take} from "rxjs";
import {LoginService} from "./login-service.service";

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {

  constructor( private loginService: LoginService, private router: Router) {}

  canActivate( route: ActivatedRouteSnapshot, router: RouterStateSnapshot): boolean | Promise<boolean> | Observable<boolean | UrlTree> {
    return this.loginService.user.pipe(
      take(1),
      map( user => {
        const isAuth = !!user;
        if ( isAuth) {
          return true;
        }
        return this.router.createUrlTree(['/']);
      }));
  }
}
