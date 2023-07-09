import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { UserSessionService } from '../services/user-session.service';
import { UserService } from '../services/user.service';
import { Observable, of } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private sessionService: UserSessionService, private userService: UserService) {}

  public canActivate(): Observable<boolean> {
    if (this.sessionService.isLogged) {
      return of(true);
    }

    return this.userService.me().pipe(
      switchMap((user: any) => {
        const token = localStorage.getItem('token');
        this.sessionService.user = { ...this.sessionService.user, ...user };
        this.sessionService.logIn(token!);
        return of(true);
      }),
      catchError(() => of(false))
    );
  }
}
