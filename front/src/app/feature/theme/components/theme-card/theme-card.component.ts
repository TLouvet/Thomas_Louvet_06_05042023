import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { Theme } from '../../interfaces/theme.interface';
import { UserSessionService } from 'src/app/services/user-session.service';
import { Subscription } from 'rxjs';
import { ThemeService } from '../../services/theme.service';

@Component({
  selector: 'app-theme-card',
  templateUrl: './theme-card.component.html',
  styleUrls: ['./theme-card.component.scss'],
})
export class ThemeCardComponent implements OnInit, OnDestroy {
  @Input() theme!: Theme;
  public userSubscribed: boolean = false;

  private destroy$: Subscription = new Subscription();
  constructor(private userSessionService: UserSessionService, private themeService: ThemeService) {}

  public toggleSubscription(): void {
    if (this.userSubscribed) {
      this.unsubscribe();
    } else {
      this.subscribe();
    }

    this.userSubscribed = !this.userSubscribed;
  }

  private subscribe(): void {
    this.themeService.subscribeToTheme(this.theme.id).subscribe({
      next: () => {
        this.userSessionService.setSubscriptions([...this.userSessionService.subscriptions, this.theme]);
      },
    });
  }

  private unsubscribe(): void {
    this.themeService.unsubscribeFromTheme(this.theme.id).subscribe({
      next: () => {
        this.userSessionService.setSubscriptions(
          this.userSessionService.subscriptions.filter((subscription) => subscription.id !== this.theme.id)
        );
      },
    });
  }

  ngOnInit(): void {
    this.userSessionService.$subscriptions().subscribe((subscriptions) => {
      this.userSubscribed = subscriptions.some((subscription) => subscription.id === this.theme.id);
    });
  }

  ngOnDestroy(): void {
    this.destroy$.unsubscribe();
  }
}
