import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {LeaveRatingModel} from "../../bookstore/dto/leave-rating.model";
import {BookService} from "../../bookstore/services/book.service";

@Component({
  selector: 'app-leave-rating',
  templateUrl: './leave-rating.component.html',
  styleUrls: ['./leave-rating.component.css']
})
export class LeaveRatingComponent implements OnInit {

  public rating: LeaveRatingModel = new LeaveRatingModel();
  stars: number[] = [1, 2, 3, 4, 5];
  selectedValue: number = 0;
  isMouseover = true;

  countStar(star: number) {
    this.isMouseover = false;
    this.selectedValue = star;
    this.rating.value = star;

  }

  addClass(star: number) {
    if (this.isMouseover) {
      this.selectedValue = star;
    }
  }

  removeClass() {
    if (this.isMouseover) {
      this.selectedValue = 0;
    }
  }

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private dialogRef: MatDialogRef<LeaveRatingComponent>, private bookService: BookService) { }

  ngOnInit(): void {
    this.rating.bookId = this.data.bookId;
    this.rating.userId = this.data.userId;
  }

  sendReview() {
    console.log(this.rating)
    this.bookService.gradeBook(this.rating).subscribe({
        next: response => {
          alert("Feedback sent!");
        },
        error: message => {
          alert("You can't send multiple feedbacks!")
        }

      }
    )
  }

  cancelReview() {
    this.dialogRef.close();
  }
}
