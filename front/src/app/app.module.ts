import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { AuthComponent } from './pages/auth/auth.component';
import { ThemeCardComponent } from './feature/theme/components/theme-card/theme-card.component';
import { ArticleCardComponent } from './feature/article/components/article-card/article-card.component';
import { MaterialModule } from './material.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ThemesPage } from './pages/themes/themes.component';
import { ComponentsModule } from './components/components.module';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { CreateArticleComponent } from './feature/article/components/create/creat-article.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SingleArticleComponent } from './feature/article/components/single/single-article.component';
import { ArticlesPage } from './feature/article/components/list/articles.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    AuthComponent,
    ThemeCardComponent,
    ArticleCardComponent,
    ArticlesPage,
    ThemesPage,
    SingleArticleComponent,
    CreateArticleComponent,
    NotFoundComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    ComponentsModule,
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }],
  bootstrap: [AppComponent],
})
export class AppModule {}
