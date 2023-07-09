import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../interfaces/comment.interface';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  constructor(private http: HttpClient) {}

  public createComment(comment: any): Observable<Comment> {
    return this.http.post<Comment>(`/api/comment`, comment);
  }

  public getArticleComments(articleId: string): Observable<Comment[]> {
    return this.http.get<Comment[]>(`/api/article/${articleId}/comments`);
  }
}
