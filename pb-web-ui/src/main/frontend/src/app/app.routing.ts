import {Routes, RouterModule} from "@angular/router";
import {LoginComponent} from "./component/login/login.component";
import {DashboardComponent} from "./component/dashboard/dashboard.component";
import {GuardService} from "./guard/index";

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: DashboardComponent, canActivate: [GuardService] },

  { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
