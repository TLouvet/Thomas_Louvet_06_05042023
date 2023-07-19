import { NgModule } from '@angular/core';
import { ArticleCardComponent } from './components/article-card/article-card.component';
import { MaterialModule } from 'src/app/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { ComponentsModule } from 'src/app/components/components.module';
import { CreateArticleComponent } from './components/create/creat-article.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { SingleArticleComponent } from './components/single/single-article.component';
import { ArticlesPage } from './components/list/articles.component';
import { ArticleService } from './services/article.service';
import { CommentService } from './services/comment.service';

@NgModule({
  declarations: [ArticleCardComponent, CreateArticleComponent, SingleArticleComponent, ArticlesPage],
  imports: [MaterialModule, BrowserModule, ComponentsModule, RouterModule, ReactiveFormsModule],
  providers: [ArticleService, CommentService],
  exports: [],
})
export class ArticleModule {}
