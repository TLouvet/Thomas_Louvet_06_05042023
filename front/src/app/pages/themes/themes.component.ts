import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ThemeService } from 'src/app/feature/theme/services/theme.service';
import { Theme } from 'src/app/models/subscription.interface';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss'],
})
export class ThemesPage implements OnInit {
  constructor(private themeService: ThemeService, private title: Title) {
    this.title.setTitle('MDD - Themes');
  }

  public themes: Theme[] = [];
  public loaded = false;

  ngOnInit(): void {
    this.themeService.getThemes().subscribe((res) => {
      this.themes = res;
      this.loaded = true;
    });
  }
}
