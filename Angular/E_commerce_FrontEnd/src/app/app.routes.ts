import { Routes } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { LogInComponent } from './log-in/log-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { AlertComponent } from './alert/alert.component';

export const routes: Routes = [
    // {
    //      path:'products',
    //      component:ProductsComponent
    // },
    {
        path:'login',
        component:LogInComponent
    },
    {
        path:'signup',
        component:SignUpComponent
    },

];
