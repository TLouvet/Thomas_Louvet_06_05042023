import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { UserSessionService } from 'src/app/services/user-session.service';

// TODO -- le user n'est pas redirigé depuis ce composant

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
})
export class AuthComponent implements OnInit {
  constructor(private userSessionService: UserSessionService, private router: Router, private title: Title) {
    this.title.setTitle('MDD - Welcome');
  }

  ngOnInit(): void {
    console.log(this.userSessionService.isLogged);
    if (this.userSessionService.isLogged) {
      this.router.navigate(['/profile']);
    }
  }
}
