import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Categoria } from '../categoria';
import { CategoriasService } from '../categorias.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-categoria',
  templateUrl: './add-categoria.component.html',
  styleUrls: ['./add-categoria.component.scss']
})
export class AddCategoriaComponent implements OnInit {

  categoria: Categoria;
  errorMessage: string;

  constructor(public dialogRef: MatDialogRef<AddCategoriaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private service: CategoriasService, private router: Router) { 
      this.categoria = <Categoria>{};
    }

  ngOnInit() {
  }

  onSubmit(categoria: Categoria) {
    this.dialogRef.close("Fue guardado.");
    categoria.id = null;
    this.service.addCategoria(categoria).subscribe(
      new_categoria => {
        this.categoria = new_categoria;
        this.gotoCategoriasList();
      },
      error => this.errorMessage = <any>error
    );
  }

  gotoCategoriasList() {
    this.router.navigate(['/categorias/showcategorias']);
  }

}
