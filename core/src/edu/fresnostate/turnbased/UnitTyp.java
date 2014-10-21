package edu.fresnostate.turnbased;

public class UnitTyp 
{
	int UnitID;
	int UnitBaseHP;
	int UnitCurentHP;
	int UnitAD;
	int UnitMovement;
	int UnitDF;
	{
	 
		 UnitID=1; 
		 switch (UnitID)
		 {
		 case 1: 
			 UnitBaseHP=10 ;
			 UnitCurentHP=10;
			 UnitAD=1;
			 UnitMovement=3;
			 UnitDF=1;
			 break;
		 case 2:
			 UnitBaseHP=10 ;
			 UnitCurentHP=10;
			 UnitAD=1;
			 UnitMovement=4;
			 UnitDF=2;
			 break;
		 case 3:
			 UnitBaseHP=10 ;
			 UnitCurentHP=10;
			 UnitAD=1;
			 UnitMovement=2;
			 UnitDF=3;
			 break;
		 }
	 }
}

