import { NgModule } from '@angular/core';
import { ButtonComponent } from './button/button.component';
import { CardListComponent } from './cardList/card-list.component';
import { HeaderComponent } from './header/header.component';
import { MaterialModule } from '../material.module';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { InputComponent } from './input/input.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LayoutComponent } from './layout/layout.component';

@NgModule({
  declarations: [ButtonComponent, CardListComponent, HeaderComponent, InputComponent, LayoutComponent],
  imports: [MaterialModule, RouterModule, CommonModule, ReactiveFormsModule],
  exports: [ButtonComponent, CardListComponent, HeaderComponent, InputComponent, LayoutComponent],
})
export class ComponentsModule {}
