import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { DashbordComponent } from './Student/dashbord/dashbord.component';
import { TdashbordComponent } from './teacher/tdashbord/tdashbord.component';

const routes: Routes = [
  { path: 'home', component: LandingPageComponent },
  { path: '', component: LandingPageComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'student/dashboard', component: DashbordComponent },
  { path: 'teacher/dashboard', component: TdashbordComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
