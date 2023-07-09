import { Component } from '@angular/core';
import { UserService } from './services/user.service';
import { UserSessionService } from './services/user-session.service';
import { ThemeService } from './feature/theme/services/theme.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { User } from './interfaces/user.interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'front';

  constructor(
    private userService: UserService,
    private userSessionService: UserSessionService,
    private themeService: ThemeService,
    private location: Location,
    private router: Router
  ) {
    if (localStorage.getItem('token')) {
      this.userService.me().subscribe({
        next: (user: User) => {
          if (this.location.path() === '') {
            this.router.navigate(['/profile']);
          }
          this.userSessionService.setUserInformation(user);
          this.userSessionService.logIn(localStorage.getItem('token')!);
          this.themeService.getUserSubscribedThemes().subscribe((res) => this.userSessionService.setSubscriptions(res));
        },
        error: () => {},
      });
    }
  }
}
