#include <stdio.h>
#include <string.h>

void WasteCategorization() //What to do
{
    int choice = 0;
    printf("\n\nWaste Categorization:\n");
    while(1)
    {
        printf("Select the type of waste:\n");
        printf("1. Organic Waste\n");
        printf("2. Inorganic Waste\n");
        printf("3. Return to Main Menu\n");
        scanf("%d", &choice);
        getchar();
        switch(choice)
        {
            case 1:
                printf("Organic Waste selected.\n");
                printf("Composting is the best option for organic waste.\n\n");
                break;
            case 2:
                printf("Inorganic Waste selected.\n");
                printf("Recycling is the best option for inorganic waste.\n\n");
                break;
            case 3:
                printf("Returning to main menu.\n");
                return; // Return to main menu
            default:
                printf("Invalid choice. Please try again.\n");
                continue;
        }
    }
}

void WasteSchedule() // gives info based on times. 
{
    
    int choice = 0;
    printf("\n\nCollection Schedule:\n");
    while(1)// inf till quit
    {
        printf("Select the day of the week:\n");
        printf("1. Monday, 2. Tuesday, 3. Wednesday, 4. Thursday, 5. Friday, 6. Saturday, 7. Sunday\n");
        printf("8. Return to Main Menu\n");
        scanf("%d", &choice);
        switch(choice)
        {
            case 1:
                printf("Pickup at 10:00 AM\n");
                printf("Recycling at 2:00 PM\n");
                break;
            case 2:
                printf("Pickup not available\n");
                printf("Recycling not available\n");
                break;
            case 3:
                printf("Pickup not available\n");
                printf("Recycling not available\n");
                break;
            case 4:
                printf("Pickup at 9:00 AM\n");
                printf("Recycling at 1:00 PM\n");
                break;
            case 5:
                printf("Pickup not availble\n");
                printf("Recycling not avaible\n");
                break;
            case 6:
                printf("Pickup not avaiable\n");
                printf("Recycling not available\n");
                break;
            case 7:
                printf("Pickup at 10:00 AM\n");
                printf("Recycling not avaiable\n");
                break;     
            case 8:
                printf("Returning to main menu.\n");
                return; // Return to main menu
            default:
                printf("Invalid choice. Please try again.\n");
                continue;  
        }
    }
}

void EduContent() // Educational Content

{
    int choice = 0;
    printf("\n\nEducational Content:\n");
    while (1)
    {
        printf("1. Recycling Categories\n");
        printf("2. Recycling Process\n");
        printf("3. Composting\n");
        printf("4. Waste Reduction Tips\n");
        printf("5. Back to Main Menu\n");
        scanf("%d", &choice);
        switch (choice)
        {
            case 1:
                printf("Organic\nPaper\nPlasic\nGlass\nMetal\nE-Waste\nMixed\n");
                break;
            case 2:
                printf("You sort out your organic and inorganic material\n");
                printf("You put your inorganic material in the recycling bin once checked\n");
                printf("You put your organic material in the compost bin once checked\n");
                break;
            case 3:
                printf("Put your non poisonous organic material in the compost bin\n");
                break;
            case 4:
                printf("Buy less junk, recycle more, compost more\n");
                break;
            case 5:
                printf("Returning to main menu.\n");
                return;
            default:
                printf("Invalid, choose an option\n");
        }
    }
}
