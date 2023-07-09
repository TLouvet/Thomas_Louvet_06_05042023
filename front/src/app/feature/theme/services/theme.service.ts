import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Theme } from '../interfaces/theme.interface';
import { Observable } from 'rxjs';
import { shareReplay } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  constructor(private http: HttpClient) {}
  private cachedThemes$?: Observable<Theme[]>;

  public getThemes(): Observable<Theme[]> {
    if (!this.cachedThemes$) {
      this.cachedThemes$ = this.http.get<Theme[]>('/api/theme').pipe(shareReplay({ refCount: true, bufferSize: 1 }));
    }

    return this.cachedThemes$;
  }

  public findOne(id: string): Observable<Theme> {
    return this.http.get<Theme>(`/api/theme/${id}`);
  }

  public getUserSubscribedThemes(): Observable<Theme[]> {
    return this.http.get<Theme[]>('/api/theme/mySubscriptions');
  }

  public subscribeToTheme(themeId: number): Observable<void> {
    return this.http.post<void>(`/api/theme/${themeId}/subscribe`, {});
  }

  public unsubscribeFromTheme(themeId: number): Observable<void> {
    return this.http.delete<void>(`/api/theme/${themeId}/unsubscribe`);
  }
}
