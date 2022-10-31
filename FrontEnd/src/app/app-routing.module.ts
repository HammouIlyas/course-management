import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './Admin/dashboard/dashboard.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { AllcoursesComponent } from './Student/allcourses/allcourses.component';
import { DashbordComponent } from './Student/dashbord/dashbord.component';
import { TdashbordComponent } from './teacher/tdashbord/tdashbord.component';

const routes: Routes = [
  { path: 'home', component: LandingPageComponent },
  { path: '', component: LandingPageComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'student/dashboard', component: DashbordComponent },
  { path: 'admin/dashboard', component: DashboardComponent },
  { path: 'student/allcourses', component: AllcoursesComponent },
  { path: 'teacher/dashboard', component: TdashbordComponent },
  { path: '**', component: LandingPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
