import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function passwordMatchValidator():ValidatorFn{
    return (control: AbstractControl): ValidationErrors | null => {
        const password = control.get('password');
        const confirmPassword = control.get('confirmPassword');
    
        if (!password || !confirmPassword) {
          return null;
        }
    
        if (confirmPassword.errors && !confirmPassword.errors['passwordMismatch']) {
          return null; // Return if other errors exist
        }
    
        if (password.value !== confirmPassword.value) {
          confirmPassword.setErrors({ passwordMismatch: true });
          
        } else {
          confirmPassword.setErrors(null); // ✅ Clear error if they match
        }
    
        return null;
      };
    }