import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MercanciaTableComponent } from './components/mercancia-table/mercancia-table.component';
import { MercanciaformComponent } from './components/mercanciaform/mercanciaform.component';
import { MercancialistComponent } from './components/mercancialist/mercancialist.component';

const routes: Routes = [
  {path:'',redirectTo:'producto',pathMatch:'full'},
  {path:'mercancia',component:MercancialistComponent},
  {path:'producto',component:MercanciaTableComponent},
  {path:'nuevo',component:MercanciaformComponent},
  {path:'editar/:id',component:MercanciaformComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
