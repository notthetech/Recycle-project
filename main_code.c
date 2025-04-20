#include <stdio.h>
#include "functions.c"

int main()// where it all ends up
{
    int choice = 0; // to make menu work 
    printf("Please select an option:\n");
    while(1)
    {
        printf("1. Waste Categorization\n");
        printf("2. Collection Schedule\n");
        printf("3. Educational Content\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);
        getchar();
        switch(choice)
        {
            case 1:
                printf("Waste Categorization selected.\n");
                WasteCategorization();
                break;
            case 2:
                printf("Collection Schedule selected.\n");
                WasteSchedule();
                break;
            case 3:
                printf("Educational Content selected.\n");
                EduContent();
                break;
            case 4:
                printf("Exiting the program.\n");
                return 0;
            default:
                printf("Invalid, choose an option\n");
        }
    }
}
