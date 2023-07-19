import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './pages/profile/profile.component';
import { AuthComponent } from './pages/auth/auth.component';
import { AuthGuard } from './guards/auth.guard';
import { UnAuthGuard } from './guards/unauth.guard';
import { CreateArticleComponent } from './feature/article/components/create/creat-article.component';
import { SingleArticleComponent } from './feature/article/components/single/single-article.component';
import { ArticlesPage } from './feature/article/components/list/articles.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { ThemesPage } from './feature/theme/components/list/themes.component';

const routes: Routes = [
  {
    path: '',
    component: AuthComponent,
    canActivate: [UnAuthGuard],
  },
  {
    path: 'auth',
    loadChildren: () => import('./feature/auth/auth.module').then((m) => m.AuthModule),
    canActivate: [UnAuthGuard],
  },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  {
    path: 'articles',
    component: ArticlesPage,
    canActivate: [AuthGuard],
  },
  {
    path: 'articles/create',
    component: CreateArticleComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'articles/:id',
    component: SingleArticleComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'themes',
    component: ThemesPage,
    canActivate: [AuthGuard],
  },
  { path: '404', component: NotFoundComponent },
  { path: '**', redirectTo: '404' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
