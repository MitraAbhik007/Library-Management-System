import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BooksIssuedComponent } from './BooksIssued.component';

describe('BooksIssuedComponent', () => {
  let component: BooksIssuedComponent;
  let fixture: ComponentFixture<BooksIssuedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BooksIssuedComponent]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BooksIssuedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
