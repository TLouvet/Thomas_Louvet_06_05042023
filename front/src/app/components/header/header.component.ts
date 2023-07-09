import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { UserSessionService } from 'src/app/services/user-session.service';
import { Observable, of, Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class HeaderComponent implements OnInit {
  public $isLogged: Observable<boolean> = of(false);

  private destroy$!: Subscription;
  constructor(private userSessionService: UserSessionService) {}
  public ROUTES = [
    { path: '/articles', label: 'Articles' },
    { path: '/themes', label: 'Thèmes' },
  ];

  ngOnInit(): void {
    this.destroy$ = this.userSessionService.$isLogged().subscribe((res) => (this.$isLogged = of(res)));
  }

  ngOnDestroy(): void {
    this.destroy$.unsubscribe();
  }
}
