import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserSessionService } from 'src/app/services/user-session.service';
import { Subscription } from 'rxjs';
import { Theme } from 'src/app/models/subscription.interface';
import { ThemeService } from 'src/app/feature/theme/services/theme.service';
import { UserService } from 'src/app/services/user.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit, OnDestroy {
  public user: any = this.userSessionService.user;
  public updateValid: boolean = false;

  constructor(
    private router: Router,
    private userSessionService: UserSessionService,
    private userService: UserService,
    private fb: FormBuilder,
    private themeService: ThemeService,
    private title: Title
  ) {
    this.title.setTitle('MDD - Profil');
  }

  public form = this.fb.group({
    username: [this.user.username, [Validators.required, Validators.minLength(3)]],
    email: [this.user.email, [Validators.required, Validators.email]],
  });

  public themesSubcriptions: Theme[] = [];
  private destroy$: Subscription = new Subscription();

  submit() {
    const request = this.form.value;
    this.userService.updateMe(request).subscribe((res) => {
      this.userSessionService.setUserInformation(res);
      this.updateValid = true;
      setTimeout(() => {
        this.updateValid = false;
      }, 3000);
    });
  }

  logout() {
    this.userSessionService.logOut();
    this.router.navigate(['/']);
  }

  ngOnInit(): void {
    this.themeService.getUserSubscribedThemes().subscribe((res) => this.userSessionService.setSubscriptions(res));
    this.destroy$ = this.userSessionService.$subscriptions().subscribe((subscriptions) => {
      this.themesSubcriptions = subscriptions;
    });
  }

  ngOnDestroy(): void {
    this.destroy$.unsubscribe();
  }
}
