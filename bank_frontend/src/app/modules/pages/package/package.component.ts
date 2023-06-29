import { Component, OnInit } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {TokenStorageService} from "../../bookstore/services/token-storage.service";
import {PackageService} from "../../bookstore/services/package.service";
import {PackageDTOModel} from "../../bookstore/dto/packageDTO.model";
import {PackageModel} from "../../bookstore/model/package.model";

@Component({
  selector: 'app-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent implements OnInit {

  packages: PackageModel[] = [];
  packageDTO: PackageDTOModel = new PackageDTOModel();
  isLoggedIn: boolean = false;



  constructor(public dialog: MatDialog, public packageService: PackageService, private tokenStorageService: TokenStorageService) {
    this.isLoggedIn = this.tokenStorageService.isLoggedIn()
  }

  ngOnInit(): void {
     this.getPackages();
  }

  createSavingsPackage() {
    this.packageDTO.id = this.tokenStorageService.getUser().id;
    this.packageDTO.type = 0;
    this.packageService.createPackage(this.packageDTO).subscribe({
        next: response => {
          alert("Account created!");
        },
        error: message => {
          alert("You arent allowed to create this package!")
        }

      }
    )
  }

  createCheckingPackage() {
    this.packageDTO.id = this.tokenStorageService.getUser().id;
    this.packageDTO.type = 1;
    this.packageService.createPackage(this.packageDTO).subscribe({
        next: response => {
          alert("Account created!");
        },
        error: message => {
          alert("You arent allowed to create this package!")
        }

      }
    )
  }

  getPackages(): void {
    this.packageService.getAll().subscribe(res => {
      this.packages = res;
      console.log(res);
      console.log(this.packages);
    });
  }

}
