#include <stdio.h>

// void Waste Categorization 
// void Collection Schedule

void WasteSchedule() // check if choice is global or just local in main
{
    //code
}

void UserTracking()// user tracking, code to create and remove user accounts

{
    //code
}
//maybe add a ranking feature to the user tracking system

void RewardSystem() // Reward System, find user points and redeem points
{
    int choice = 0;
    printf("\n\nReward System:\n");
    while (1)
    {
        printf("1. View Points\n");
        printf("2. Redeem Points\n");
        printf("3. Back to Main Menu\n");
        scanf("%d", &choice);
        switch(choice)
        {
            //code for reward system calling from other files
            default:
                printf("Invalid, choose an option\n");
        }
    }
}

void EduContent() // Educational Content
{
    int choice = 0;
    printf("\n\nEducational Content:\n");
    while (1)
    {
        printf("1. Waste Categories\n");
        printf("2. Recycling Process\n");
        printf("3. Composting\n");
        printf("4. Waste Reduction Tips\n");
        printf("5. Back to Main Menu\n");
        scanf("%d", &choice);
        switch (choice)
        {
            case 1:
                printf("Info");
                break;
            case 2:
                printf("Info");
                break;
            case 3:
                printf("Info");
                break;
            case 4:
                printf("Info");
                break;
            case 5:
                return;
            default:
                printf("Invalid, choose an option\n");
        }
    }
}

int main()
{
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
