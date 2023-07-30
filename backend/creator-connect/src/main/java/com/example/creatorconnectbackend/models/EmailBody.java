/**
 * -----------------------------------------------------------------------------
 *                        Email Body Model Class
 * -----------------------------------------------------------------------------
 * 
 * Purpose:
 * The 'EmailBody' class acts as a representation for the body content 
 * of an email, primarily capturing the recipient's email address. This 
 * structure is typically used when sending out emails in the context of 
 * the 'com.example.creatorconnectbackend' application.
 * 
 * Key Attribute:
 * - email: The email address to which a certain email needs to be sent. 
 *   It captures the core content of the email body in its current scope.
 * 
 * Constraints:
 * - email: This attribute should adhere to standard email formatting 
 *   conventions. Any deviation from this format will raise validation 
 *   errors as ensured by the `@Email` annotation.
 * 
 * Usage:
 * The 'EmailBody' model can be used in various scenarios within the application, 
 * such as sending password reset links, registration confirmations, or any other 
 * email-based notifications.
 * 
 * Enhancements (Future Consideration):
 * - Expand the class to include other attributes like 'subject', 'messageBody', 
 *   or even 'attachment', to provide a more comprehensive representation of an 
 *   email.
 * - Incorporate additional validation and safety measures, such as ensuring 
 *   non-malicious content within emails.
 * 
 * -----------------------------------------------------------------------------
 */

package com.example.creatorconnectbackend.models;

import org.hibernate.validator.constraints.Email;

public class EmailBody {

    @Email(message = "Email should be valid")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
