#include <stdio.h>
#include "functions.c"
/*add more includes if needed
don't forget to put functions in seperate files
add more comments
Use of File handling concepts, Structs, ADS
*/


/*
limit scanf to only take first char and then based off that
*/
int main()// add struct to include members and their points
{
    typedef struct Data
    {
        char name[50];
        int points;
    } user;
    //figure out how to make them as pointers and be used
    int choice = 0;
    printf("Welcome to the Waste Management System!\n");
    printf("Please select an option:\n");
    
    while(1)
    {
        printf("1. Waste Categorization\n");
        printf("2. Collection Schedule\n");
        printf("3. User Tracking\n");
        printf("4. Reward System\n");
        printf("5. Educational Content\n");
        printf("6. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);
        getchar();
        switch(choice)
        {
            case 1:
                printf("Waste Categorization selected.\n");
                // Call waste categorization function here
                break;
            case 2:
                printf("Collection Schedule selected.\n");
                // Call collection schedule function here
                break;
            case 3:
                printf("User Tracking selected.\n");
                UserTracking();
                break;
            case 4:
                printf("Reward System selected.\n");
                RewardSystem();
                break;
            case 5:
                printf("Educational Content selected.\n");
                EduContent();
                break;
            case 6:
                printf("Exiting the program.\n");
                return 0;
            default:
                printf("Invalid, choose an option\n");
        }
    }
}
