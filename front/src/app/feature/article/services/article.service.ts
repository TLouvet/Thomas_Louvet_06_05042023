import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article } from '../interfaces/article.interface';
import { Observable } from 'rxjs';
import { CreateArticleRequest } from '../interfaces/createArticleRequest.interface';

@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  private pathService = 'article';
  constructor(private http: HttpClient) {}

  public getArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(`/api/${this.pathService}/feed`);
  }

  public getArticle(id: string): Observable<Article> {
    return this.http.get<Article>(`/api/${this.pathService}/${id}`);
  }

  public createArticle(article: CreateArticleRequest): Observable<Article> {
    return this.http.post<Article>(`/api/${this.pathService}`, article);
  }
}
