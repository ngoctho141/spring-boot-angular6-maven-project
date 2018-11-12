import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { CarService } from '../car.service';
import { GiphyService } from '../giphy.service';
import { NgForm } from '@angular/forms';

@Component({
    selector: 'app-car-edit',
    templateUrl: './car-edit.component.html',
    styleUrls: ['./car-edit.component.css']
})
export class CarEditComponent implements OnInit, OnDestroy {
    car: any = {};
    sub: Subscription;
    ngOnDestroy(): void {
        this.sub.unsubscribe();
    }

    constructor(private route: ActivatedRoute,
                private router: Router,
                private carService: CarService,
                private giphyService: GiphyService) { }

    ngOnInit() {
        this.sub = this.route.params.subscribe(params => {
            const id = params['id'];
            if (id) {
                this.carService.get(id).subscribe((car: any) => {
                    if (car) {
                        this.car = car;
                        this.car.href = car._links.self.href;
                        this.giphyService.get(car.name).subscribe(url => car.giphyUrl = url);
                    } else {
                        console.log(`Car with id '${id}' not found, returning to list`);
                        this.gotoList();
                    }
                });
            }
        });
    }
    gotoList() {
        this.router.navigate(['/car-list']);
    }

    save(form: NgForm) {
        this.carService.save(form).subscribe(() => {
            this.gotoList();
        }, error => {
            console.error(error);
        });
    }

    remove(href) {
        this.carService.remove(href).subscribe(() => {
            this.gotoList();
        }, error => console.error(error));
    }

}
