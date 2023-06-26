import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { BookWithAuthorsModel } from '../../bookstore/dto/book-with-authors.model';
import { BookService } from '../../bookstore/services/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  addBookForm = new FormGroup({
    name: new FormControl<string | undefined>(undefined),    
    authorFirstName: new FormControl<string | undefined>(undefined),    
    authorLastName: new FormControl<string | undefined>(undefined),    
    genre: new FormControl<number | undefined>(undefined),    
    price: new FormControl<number | undefined>(undefined),    
  })

  constructor(private router: Router, private bookService: BookService) { }

  ngOnInit(): void {
  }

  public addBook() {
    const newBook: BookWithAuthorsModel = new BookWithAuthorsModel({
      name: this.addBookForm.controls.name.value!,
      authorFirstName: this.addBookForm.controls.authorFirstName.value!,
      authorLastName: this.addBookForm.controls.authorLastName.value!,
      genre: this.addBookForm.controls.genre.value!,
      price: this.addBookForm.controls.price.value!,
    })

    console.log(newBook)

    this.bookService.addBook(newBook).subscribe({
      next: response => {
        console.log(response)
        alert("Book added successfully!");
        this.router.navigate(['']).then(
          ()=>{
            window.location.reload();
          }
        );
      },
      error: message => {
        console.log(message.Error)
        alert("haha, i'm in danger")
      }
    })
  }

}
