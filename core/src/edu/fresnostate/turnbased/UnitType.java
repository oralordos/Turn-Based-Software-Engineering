package edu.fresnostate.turnbased;

public class UnitType {

	public enum Unitstates {
		INFANTRY(10, 2, 3, 4,1,200), TANK(10, 4, 4, 4,1,1000), MONSTER(10, 3, 4, 5,2,1200);

		final int UnitBaseHP;

		final int UnitAD;
		final int UnitMovement;
		final int UnitDF;
		final int UnitRang ;
		final int Unitcost;

		Unitstates(int UnitBaseHP, int UnitDF, int UnitAD, int UnitMovement,int UnitRang,int Unitcost) {
			this.UnitBaseHP = UnitBaseHP;
			this.UnitDF = UnitDF;
			this.UnitAD = UnitAD;
			this.UnitMovement = UnitMovement;
			this.UnitRang=UnitRang;
			this.Unitcost=Unitcost;
		}

	}
}

// switch (UnitID)
// {
// case 1:
// UnitBaseHP=10 ;
// UnitCurentHP=10;
// UnitAD=1;
// UnitMovement=3;
// UnitDF=1;
// break;
// case 2:
// UnitBaseHP=10 ;
// UnitCurentHP=10;
// UnitAD=1;
// UnitMovement=4;
// UnitDF=2;
// break;
// case 3:
// UnitBaseHP=10 ;
// UnitCurentHP=10;
// UnitAD=1;
// UnitMovement=2;
// UnitDF=3;
// break;
// }
// }

