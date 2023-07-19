import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ThemeService } from 'src/app/feature/theme/services/theme.service';
import { Theme } from 'src/app/feature/theme/interfaces/subscription.interface';
import { ArticleService } from '../../services/article.service';
import { CreateArticleRequest } from '../../interfaces/createArticleRequest.interface';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.scss'],
})
export class CreateArticleComponent implements OnInit {
  public themes: Theme[] = [];
  public articleForm = this.fb.group({
    title: ['', [Validators.required, Validators.minLength(3)]],
    content: ['', [Validators.required, Validators.minLength(3)]],
    theme: [0, [Validators.required, Validators.min(1)]],
  });
  public onError: boolean = false;

  constructor(
    private themeService: ThemeService,
    public fb: FormBuilder,
    private router: Router,
    private articleService: ArticleService,
    private title: Title
  ) {
    this.title.setTitle('MDD - Créer un article');
  }

  public submit() {
    const article = this.articleForm.value as CreateArticleRequest;
    this.articleService.createArticle(article).subscribe({
      next: () => {
        this.router.navigate(['/articles']);
      },
      error: () => (this.onError = true),
    });
  }

  ngOnInit(): void {
    this.themeService.getThemes().subscribe((res) => {
      this.themes = res;
    });
  }
}
