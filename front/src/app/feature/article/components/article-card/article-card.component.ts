import { Component, Input, OnInit } from '@angular/core';
import { Article } from 'src/app/feature/article/interfaces/article.interface';

@Component({
  selector: 'article-card',
  templateUrl: './article-card.component.html',
  styleUrls: ['./article-card.component.scss'],
})
export class ArticleCardComponent implements OnInit {
  @Input() article!: Article;

  public formatedContent: string = '';

  ngOnInit(): void {
    this.formatedContent =
      this.article.content.length > 250 ? this.article.content.slice(0, 250) + '...' : this.article.content;
  }
}
