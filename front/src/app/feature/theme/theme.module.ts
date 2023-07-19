import { NgModule } from '@angular/core';
import { ThemeCardComponent } from './components/theme-card/theme-card.component';
import { MaterialModule } from 'src/app/material.module';
import { ThemesPage } from './components/list/themes.component';
import { ThemeService } from './services/theme.service';
import { ComponentsModule } from 'src/app/components/components.module';
import { BrowserModule } from '@angular/platform-browser';

@NgModule({
  declarations: [ThemeCardComponent, ThemesPage],
  imports: [MaterialModule, ComponentsModule, BrowserModule],
  exports: [ThemeCardComponent],
  providers: [ThemeService],
})
export class ThemeModule {}
