import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TakeBookComponent } from './take-book.component';

describe('TakeBookComponent', () => {
  let component: TakeBookComponent;
  let fixture: ComponentFixture<TakeBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TakeBookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TakeBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
