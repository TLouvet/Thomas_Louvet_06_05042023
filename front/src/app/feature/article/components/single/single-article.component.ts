import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../services/article.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Article } from '../../interfaces/article.interface';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../interfaces/comment.interface';
import { FormBuilder, Validators } from '@angular/forms';
import { CreateCommentRequest } from '../../interfaces/createComment.interface';
import { ThemeService } from 'src/app/feature/theme/services/theme.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'single-article',
  templateUrl: './single-article.component.html',
  styleUrls: ['./single-article.component.scss'],
})
export class SingleArticleComponent implements OnInit {
  constructor(
    private articleService: ArticleService,
    private route: ActivatedRoute,
    private commentService: CommentService,
    private themeService: ThemeService,
    private formBuilder: FormBuilder,
    private title: Title
  ) {}

  public article: Article = {} as Article;
  public comments: Comment[] = [];
  public theme = '';
  private articleId: string = '';
  public loaded = false;

  public commentForm = this.formBuilder.group({
    content: ['', [Validators.required, Validators.minLength(3)]],
  });

  submitComment() {
    const comment = this.commentForm.value as CreateCommentRequest;
    comment.article = Number(this.articleId);
    this.commentService.createComment(comment).subscribe((res) => {
      this.comments.push(res);
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.articleId = params.get('id') as string;
    });

    this.articleService.getArticle(this.articleId).subscribe((res) => {
      this.article = res;
      this.title.setTitle(`MDD - ${res.title}`);
      this.themeService.findOne(res.theme).subscribe((res) => {
        this.theme = res.name;
        this.loaded = true;
      });
    });

    this.commentService.getArticleComments(this.articleId).subscribe((res) => {
      this.comments = res;
    });
  }
}
