package nz.ac.ara.macklei.TAMdemo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTests {

	public GameModel game;
	public Loadable gameLoader;
	public Saveable gameSaver;
	
	@Before
	public void setup() {
		game = new GameModel();
		gameLoader = (Loadable)game;
		gameSaver = (Saveable)game;
		game.setWidthAcross(7);
		game.setDepthDown(7);
		
		/*
		 * Set basic boundary
		 *		5x5 playable space
		 */
		//LEVEL LOADED
		
		for (int i = 0; i < gameSaver.getWidthAcross(); i++) {
			Point point = new Pointer(i, 0);
			gameLoader.addWallAbove(point);
		}
		for (int j = 0; j < gameSaver.getDepthDown(); j++) {
			Point point = new Pointer(0, j);
			gameLoader.addWallLeft(point);
		}
		for (int k = 0; k < gameSaver.getWidthAcross(); k++) {
			Point point = new Pointer(k, gameSaver.getDepthDown() - 1);
			gameLoader.addWallAbove(point);
		}
		for (int l = 0; l < gameSaver.getDepthDown(); l++) {
			Point point = new Pointer(gameSaver.getWidthAcross() - 1, l);
			gameLoader.addWallLeft(point);
		}
		
		gameLoader.addExit(new Pointer(5, 3));
	}
	
	//=================================================
	//Level
	//=================================================
	
	//MINE 1
		@Test
		public void isWidth() {
			int expected = 7;
			int actual = game.getWidthAcross();
			
			assertEquals(expected, actual);
		}
		
	//MINE 2
		@Test
		public void isDepth() {
			int expected = 7;
			int actual = game.getDepthDown();
			
			assertEquals(expected, actual);
		}
	
	//Mine 8
		@Test
		public void addWallLeft() {
			Point whereWall = new Pointer(2,3);
			gameLoader.addWallLeft(whereWall);
			
			Wall expected = Wall.SOMETHING;
			Wall actual = gameSaver.whatsLeft(whereWall);
			assertEquals(expected, actual);
		}
		
	//Mine 9
		@Test
		public void addWallTop() {
			Point whereWall = new Pointer(1,2);
			gameLoader.addWallAbove(whereWall);
			
			Wall expected = Wall.SOMETHING;
			Wall actual = gameSaver.whatsAbove(whereWall);
			assertEquals(expected, actual);
		}
		
		@Test
		public void isExit() {
			Point whereExit = new Pointer(5,3);
			
			Point expected = whereExit;
			Point actual = gameSaver.wheresExit();
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());			
		}
		
		//=================================================
		//Theseus
		//=================================================
		
	//MINE 3
		@Test
		public void canGiveTheseusLocation() {
			Point whereTheseus = new Pointer(3,3);
			gameLoader.addTheseus(whereTheseus);
			Point expected = whereTheseus;
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
	//Mine 10
		@Test 
		public void moveTheseus_upOne() {
			Point whereThes = new Pointer(3,3);
			game.addTheseus(whereThes);
			
			game.moveTheseus(Directions.UP);
			
			Point expected = new Pointer(3,2);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
	//Mine 11
		@Test 
		public void moveTheseus_leftOne() {
			Point whereThes = new Pointer(3,3);
			game.addTheseus(whereThes);
			
			game.moveTheseus(Directions.LEFT);
			
			Point expected = new Pointer(2,3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
	//Mine 12
		@Test 
		public void moveTheseus_rightOne() {
			Point whereThes = new Pointer(3,3);
			game.addTheseus(whereThes);
			
			game.moveTheseus(Directions.RIGHT);
			
			Point expected = new Pointer(4,3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
	//Mine 13
		@Test 
		public void moveTheseus_downOne() {
			Point whereThes = new Pointer(3,3);
			game.addTheseus(whereThes);
			
			game.moveTheseus(Directions.DOWN);
			
			Point expected = new Pointer(3,4);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_up_wallAbove_fail() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallTop = new Pointer(3, 3);
			gameLoader.addWallAbove(wallTop);
			
			game.moveTheseus(Directions.UP);
			
			Point expected = new Pointer(3, 3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_up_wallLeft_success() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallLeft = new Pointer(3, 3);
			gameLoader.addWallLeft(wallLeft);
			
			game.moveTheseus(Directions.UP);
			
			Point expected = new Pointer(3, 2);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_up_wallRight_success() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallRight = new Pointer(4, 3);
			gameLoader.addWallLeft(wallRight);
			
			game.moveTheseus(Directions.UP);
			
			Point expected = new Pointer(3, 2);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_up_wallDown_success() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallDown = new Pointer(3, 4);
			gameLoader.addWallAbove(wallDown);
			
			game.moveTheseus(Directions.UP);
			
			Point expected = new Pointer(3, 2);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_right_wallAbove_success() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallTop = new Pointer(3, 3);
			gameLoader.addWallAbove(wallTop);
			
			game.moveTheseus(Directions.RIGHT);
			
			Point expected = new Pointer(4, 3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_right_wallLeft_success() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallLeft = new Pointer(3, 3);
			gameLoader.addWallLeft(wallLeft);
			
			game.moveTheseus(Directions.RIGHT);
			
			Point expected = new Pointer(4, 3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_right_wallRight_fail() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallRight = new Pointer(4, 3);
			gameLoader.addWallLeft(wallRight);
			
			game.moveTheseus(Directions.RIGHT);
			
			Point expected = new Pointer(3, 3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_right_wallDown_success() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallDown = new Pointer(3, 4);
			gameLoader.addWallAbove(wallDown);
			
			game.moveTheseus(Directions.RIGHT);
			
			Point expected = new Pointer(4, 3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_down_wallAbove_success() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallTop = new Pointer(3, 3);
			gameLoader.addWallAbove(wallTop);
			
			game.moveTheseus(Directions.DOWN);
			
			Point expected = new Pointer(3, 4);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_down_wallLeft_success() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallLeft = new Pointer(3, 3);
			gameLoader.addWallLeft(wallLeft);
			
			game.moveTheseus(Directions.DOWN);
			
			Point expected = new Pointer(3, 4);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_down_wallRight_success() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallRight = new Pointer(4, 3);
			gameLoader.addWallLeft(wallRight);
			
			game.moveTheseus(Directions.DOWN);
			
			Point expected = new Pointer(3, 4);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_down_wallDown_fail() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallDown = new Pointer(3, 4);
			gameLoader.addWallAbove(wallDown);
			
			game.moveTheseus(Directions.DOWN);
			
			Point expected = new Pointer(3, 3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_left_wallAbove_success() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallTop = new Pointer(3, 3);
			gameLoader.addWallAbove(wallTop);
			
			game.moveTheseus(Directions.LEFT);
			
			Point expected = new Pointer(2, 3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_left_wallLeft_fail() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallLeft = new Pointer(3, 3);
			gameLoader.addWallLeft(wallLeft);
			
			game.moveTheseus(Directions.LEFT);
			
			Point expected = new Pointer(3, 3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_left_wallRight_success() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallRight = new Pointer(4, 3);
			gameLoader.addWallLeft(wallRight);
			
			game.moveTheseus(Directions.LEFT);
			
			Point expected = new Pointer(2, 3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveTheseus_left_wallDown_success() {
			Point theseusAt = new Pointer(3, 3);
			gameLoader.addTheseus(theseusAt);
			Point wallDown = new Pointer(3, 4);
			gameLoader.addWallAbove(wallDown);
			
			game.moveTheseus(Directions.LEFT);
			
			Point expected = new Pointer(2, 3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		//Should win game
		@Test
		public void moveTheseus_right_toExit_WinState() {
			Point theseusAt = new Pointer(4, 3);
			gameLoader.addTheseus(theseusAt);
			
			game.moveTheseus(Directions.RIGHT);
			
			Point expected = new Pointer(5,3);
			Point actual = gameSaver.wheresTheseus();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		// Testing Theseus pause
		@Test
		public void moveTheseusPause_SpacePressed_TheseusSkipTurn_moveMinotaur() {
			Point whereTheseus = new Pointer(1,1);
			gameLoader.addTheseus(whereTheseus);
			Point whereMinotaur = new Pointer(4,4);
			gameLoader.addMinotaur(whereMinotaur);
			
			game.moveTheseus(Directions.SPACE);
			
			Point theExpected = whereTheseus;
			Point theActual = gameSaver.wheresTheseus();
			Point minExpected = new Pointer(2,4);
			Point minActual = gameSaver.wheresMinotaur();
			
			assertEquals(theExpected.across(), theActual.across());
			assertEquals(theExpected.down(), theActual.down());
			assertEquals(minExpected.across(), minActual.across());
			assertEquals(minExpected.down(), minActual.down());
			
		}


		//=================================================
		//Minotaur
		//=================================================
		
	//MINE 4
		@Test
		public void canGiveMinotaurLocation() {
			Point whereMinotaur = new Pointer(1,3);
			gameLoader.addMinotaur(whereMinotaur);
			Point expected = whereMinotaur;
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
	
	
	//MINE 5
		@Test
		public void moveMinotaur_upTwice() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(3, 0);
			gameLoader.addTheseus(whereThes);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(3,1);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
	//MINE 6
		@Test
		public void moveMinotaur_downTwice() {
			Point whereMin = new Pointer(3, 0);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(3, 3);
			gameLoader.addTheseus(whereThes);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(3,2);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		//MINE 7
		@Test
		public void moveMinotaur_leftTwice() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(0, 3);
			gameLoader.addTheseus(whereThes);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(1,3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		//MINE 7
		@Test
		public void moveMinotaur_rightTwice() {
			Point whereMin = new Pointer(0, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(3, 3);
			gameLoader.addTheseus(whereThes);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(2,3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_rightUp() {
			Point whereMin = new Pointer(2, 4);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(3, 2);
			gameLoader.addTheseus(whereThes);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(3, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		// TESTS GAME LOST
		@Test
		public void moveMinotaurUp_oneSpaceBetween_upTwo_theseusDead() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(3, 1);
			gameLoader.addTheseus(whereThes);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(3, 1);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		// TESTS GAME LOST
		@Test
		public void moveMinotaurDown_oneSpaceBetween_downTwo_theseusDead() {
			Point whereMin = new Pointer(3, 1);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(3, 3);
			gameLoader.addTheseus(whereThes);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(3, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaurUp_oneSpaceBetweenButWall_upNone() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(3, 1);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(3, 3);
			gameLoader.addWallAbove(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(3, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaurUp_twoSpacesBetweenButWall_upOne() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(3, 0);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(3, 2);
			gameLoader.addWallAbove(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(3, 2);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaurRight_oneSpaceBetweenButWall_rightNone() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(5, 3);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(4, 3);
			gameLoader.addWallLeft(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(3, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaurRight_oneSpaceBetweenButWall_rightOne() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(6, 3);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(5, 3);
			gameLoader.addWallLeft(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(4, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaurDown_oneSpaceBetweenButWall_downNone() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(3, 5);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(3, 4);
			gameLoader.addWallAbove(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(3, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaurDown_twoSpaceBetweenButWall_downOne() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(3, 6);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(3, 5);
			gameLoader.addWallAbove(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(3, 4);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaurDown_oneSpaceBetweenButWall_leftNone() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(1, 3);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(3, 3);
			gameLoader.addWallLeft(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(3, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaurDown_twoSpacesBetweenButWall_leftOne() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(0, 3);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(2, 3);
			gameLoader.addWallLeft(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(2, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_RightDown_wall_downRight() {
			Point whereMin = new Pointer(0, 0);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(2, 1);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(1, 0);
			gameLoader.addWallLeft(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(1, 1);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_RightDown_walls_downDown() {
			Point whereMin = new Pointer(0, 0);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(2, 1);
			gameLoader.addTheseus(whereThes);
			Point whereWall1 = new Pointer(1, 0);
			gameLoader.addWallLeft(whereWall1);
			Point whereWall2 = new Pointer(1, 1);
			gameLoader.addWallLeft(whereWall2);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(0, 1);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_RightDown_rightDown() {
			Point whereMin = new Pointer(0, 0);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(1, 2);
			gameLoader.addTheseus(whereThes);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(1, 1);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_RightDown_wall_rightNone() {
			Point whereMin = new Pointer(0, 0);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(1, 2);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(1, 1);
			gameLoader.addWallAbove(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(1, 0);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_RightDown_wallCorner_rightNone() {
			Point whereMin = new Pointer(0, 0);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(2, 1);
			gameLoader.addTheseus(whereThes);
			Point whereWall1 = new Pointer(2, 0);
			gameLoader.addWallLeft(whereWall1);
			Point whereWall2 = new Pointer(1, 1);
			gameLoader.addWallAbove(whereWall2);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(1, 0);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_RightDown_2walls_downDown() {
			Point whereMin = new Pointer(0, 0);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(1, 2);
			gameLoader.addTheseus(whereThes);
			Point whereWall1 = new Pointer(1, 0);
			gameLoader.addWallLeft(whereWall1);
			Point whereWall2 = new Pointer(1, 1);
			gameLoader.addWallLeft(whereWall2);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(0, 2);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_downLeft_wall_leftDown() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(2, 5);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(3, 4);
			gameLoader.addWallAbove(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(2, 4);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_downLeft_2walls_leftNone() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(2, 5);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(3, 4);
			gameLoader.addWallAbove(whereWall);
			Point whereWall2 = new Pointer(2, 4);
			gameLoader.addWallAbove(whereWall2);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(2, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_downLeft_leftLeft() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(1, 4);
			gameLoader.addTheseus(whereThes);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(1, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_downLeft_wall_leftLeft() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(1, 4);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(3, 4);
			gameLoader.addWallLeft(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(1, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_downLeft_wall_leftLeft2() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(1, 4);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(3, 4);
			gameLoader.addWallAbove(whereWall);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(1, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_downLeft_wallCorner_leftLeft() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(2, 5);
			gameLoader.addTheseus(whereThes);
			Point whereWall = new Pointer(3, 4);
			gameLoader.addWallLeft(whereWall);
			Point whereWall2 = new Pointer(3, 5);
			gameLoader.addWallAbove(whereWall2);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(2, 4);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}
		
		@Test
		public void moveMinotaur_downLeft_2wallsCorner_leftNone() {
			Point whereMin = new Pointer(3, 3);
			gameLoader.addMinotaur(whereMin);
			Point whereThes = new Pointer(1, 4);
			gameLoader.addTheseus(whereThes);
			Point whereWall0 = new Pointer(2, 3);
			gameLoader.addWallLeft(whereWall0);
			Point whereWall1 = new Pointer(2, 4);
			gameLoader.addWallAbove(whereWall1);
			Point whereWall2 = new Pointer(3, 4);
			gameLoader.addWallAbove(whereWall2);
			
			game.moveMinotaur();
			game.moveMinotaur();
			
			Point expected = new Pointer(2, 3);
			Point actual = gameSaver.wheresMinotaur();
			
			assertEquals(expected.across(), actual.across());
			assertEquals(expected.down(), actual.down());
		}


}