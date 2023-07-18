import { Component } from '@angular/core';
import { Article } from 'src/app/feature/article/interfaces/article.interface';
import { ArticleService } from 'src/app/feature/article/services/article.service';
import { OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'articles-page',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss'],
})
export class ArticlesPage implements OnInit {
  constructor(private articleService: ArticleService, private title: Title) {
    this.title.setTitle('MDD - Articles');
  }
  public articles: Article[] = [];
  public loaded = false;
  public sortingASC: boolean = true;

  ngOnInit(): void {
    this.articleService.getArticles().subscribe({
      next: (articles) => {
        this.articles = articles;
        this.loaded = true;
      },
      error: (error) => console.error(error),
    });
  }

  public handleSort(): void {
    this.sortingASC = !this.sortingASC;
    this.articles.sort((a, b) => {
      if (this.sortingASC) {
        return a.id - b.id;
      } else {
        return b.id - a.id;
      }
    });
  }
}
