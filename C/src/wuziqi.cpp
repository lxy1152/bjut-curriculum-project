#include<stdio.h>
#include<graphics.h>
int draw_choice(int n);
void draw_chess(int n);
void player_one(int a[][15], int b[][15],int c[2]);
void player_two(int a[][15], int b[][15],int c[2]);
void play(int chessboard[15][15],int c[2]);
int winner_plo(int a[][15],int c[2]);
int winner_plt(int b[][15],int c[2]);
void analyze(int chessboard[15][15],long score[15][15][2]);
void Findmax(long score[15][15][2], int chessboard[15][15],int coo[2]);
int winner(int c[2], int n,int chessboard[15][15]);
void Rungame(int x);
PIMAGE bkg,chess,black,white,vic_b,vic_w,vic_null;
int main()
{
	Rungame(1);
	return 0;
}
int draw_choice(int n)
{
	int result = 0;
	mouse_msg mouse = { 0 };
	bkg = newimage();
	black = newimage();
	white = newimage();
	vic_b = newimage();
	vic_w = newimage();
	vic_null = newimage();
	setcaption("Îå×ÓÆå");
	getimage(bkg, ".\\image\\background.jpg");
	getimage(black, ".\\image\\black.png");
	getimage(white, ".\\image\\white.png");
	getimage(vic_b, ".\\image\\vic_black.png");
	getimage(vic_w, ".\\image\\vic_white.png");
	getimage(vic_null, ".\\image\\vic_null.png");
	while (1)
	{
		putimage(0, 0, bkg);
		while (mousemsg())
		{
			mouse = getmouse();
			if (mouse.is_move())
			{
				mousepos(&mouse.x, &mouse.y);
			}
			if (((mouse.x) >= 480) && ((mouse.x) <= 650) )
			{
				if ((mouse.y >= 240) && (mouse.y <= 262))
				{
					if ((mouse.is_left()) && (mouse.is_down()))
					{
						result = 1;
						delimage(bkg);
						return result;
					}
				}
				if ((mouse.y >= 300) && (mouse.y <= 350))
				{
					if ((mouse.is_left()) && (mouse.is_down()))
					{
						result = 2;
						delimage(bkg);
						cleardevice();
						return result;
					}
				}
				if ((mouse.y >= 395) && (mouse.y) <= 425)
				{
					if ((mouse.is_left()) && (mouse.is_down()))
					{
						result = 0;
						delimage(bkg);
						return result;
					}
				}
			}
		}
	}
}
void draw_chess(int n)
{
	int i;
	chess = newimage();
	getimage(chess, ".\\image\\chess.jpg");
	putimage(0, 0, chess);
	for (i = 125; i <= 475; i = i + 25)//hengxian
	{
		setcolor(BLACK);
		line(375, i, 725, i);
		if (i == 125 || i == 475)
			line(375, i-1, 725, i-1);
	}
	for (i =375; i <= 725; i = i + 25)
	{
		setcolor(BLACK);
		line(i, 125, i, 475);
		if (i==375||i==725)
			line(i+1, 125, i+1, 475);
	}
	return;
}
void player_one(int a[][15], int b[][15],int c[2])
{
	mouse_msg Mouse = { 0 };
	while (1)
	{
		Mouse = getmouse();
		if (Mouse.is_move())
			mousepos(&Mouse.x,	&Mouse.y);
		if (((Mouse.x) >= 369) && ((Mouse.x) <= 736) && ((Mouse.y) >= 119) && ((Mouse.y) <= 486))
		{
			if ((Mouse.x % 25 >= 0) && (Mouse.x % 25 <= 10) && (Mouse.y % 25 >= 0) && (Mouse.y % 25 <= 10) )//youxia
			{
				if ((Mouse.is_left()) && (Mouse.is_down()))
				{
					if (b[Mouse.y / 25 - 5][Mouse.x / 25 - 15] != 1)
					{
						putimage_transparent(NULL, black, (Mouse.x / 25 * 25-10), (Mouse.y / 25 * 25-10), 0, 0, 0, 0, 0);
						a[Mouse.y / 25 - 5][Mouse.x / 25 - 15] = 1;
						c[0] = (Mouse.y / 25 - 5);
						c[1] = (Mouse.x / 25 - 15);
						break;
					}
				}
			}
			if ((Mouse.x % 25 >= 0) && (Mouse.x % 25 <= 10) && (Mouse.y % 25 >= 16) && (Mouse.y % 25 <= 24))//youshang
			{
				if ((Mouse.is_left()) && (Mouse.is_down()))
				{
					if (b[Mouse.y / 25 - 4][Mouse.x / 25 - 15] != 1)
					{
						putimage_transparent(NULL, black, (Mouse.x / 25 * 25 - 10), ((Mouse.y / 25+1) * 25 - 10), 0, 0, 0, 0, 0);
						a[Mouse.y / 25 - 4][Mouse.x / 25 - 15] = 1;
						c[0] = (Mouse.y / 25 - 4);
						c[1] = (Mouse.x / 25 - 15);
						break;
					}
				}
			}
			if ((Mouse.x % 25 >= 16) && (Mouse.x % 25 <= 24) && (Mouse.y % 25 >= 16) && (Mouse.y % 25 <= 24))//zuoshang
			{
				if ((Mouse.is_left()) && (Mouse.is_down()))
				{
					if (b[Mouse.y / 25 - 4][Mouse.x / 25 - 14] != 1)
					{
						putimage_transparent(NULL, black, ((Mouse.x / 25+1) * 25 - 10), ((Mouse.y / 25 + 1) * 25 - 10), 0, 0, 0, 0, 0);
						a[Mouse.y / 25 - 4][Mouse.x / 25 - 14] = 1;
						c[0] = (Mouse.y / 25 - 4);
						c[1] = (Mouse.x / 25 - 14);
						break;
					}
				}
			}
			if ((Mouse.x % 25 >= 16) && (Mouse.x % 25 <= 24) && (Mouse.y % 25 >= 0) && (Mouse.y % 25 <= 10))//zuoxia
			{
				if ((Mouse.is_left()) && (Mouse.is_down()))
				{
					if (b[Mouse.y / 25 - 5][Mouse.x / 25 - 14] != 1)
					{
						putimage_transparent(NULL, black, ((Mouse.x / 25 + 1) * 25 - 10), (Mouse.y / 25  * 25 - 10), 0, 0, 0, 0, 0);
						a[Mouse.y / 25 - 5][Mouse.x / 25 - 14] = 1;
						c[0] = (Mouse.y / 25 - 5);
						c[1] = (Mouse.x / 25 - 14);
						break;
					}
				}
			}
		}
	}
	return;
}
void player_two(int a[][15], int b[][15],int c[2])
{
	mouse_msg Mouse = { 0 };
	while (1)
	{
		Mouse = getmouse();
		if (Mouse.is_move())
			mousepos(&Mouse.x, &Mouse.y);
		if (((Mouse.x) >= 369) && ((Mouse.x) <= 736) && ((Mouse.y) >= 119) && ((Mouse.y) <= 486))
		{
			if ((Mouse.x % 25 >= 0) && (Mouse.x % 25 <= 10) && (Mouse.y % 25 >= 0) && (Mouse.y % 25 <= 10))//youxia
			{
				if ((Mouse.is_left()) && (Mouse.is_down()))
				{
					if (a[Mouse.y / 25 - 5][Mouse.x / 25 - 15] != 1)
					{
						putimage_transparent(NULL, white, (Mouse.x / 25 * 25 - 10), (Mouse.y / 25 * 25 - 10), 0, 0, 0, 0, 0);
						b[Mouse.y / 25 - 5][Mouse.x / 25 - 15] = 1;
						c[0] = (Mouse.y / 25 - 5);
						c[1] = (Mouse.x / 25 - 15);
						break;
					}
				}
			}
			if ((Mouse.x % 25 >= 0) && (Mouse.x % 25 <= 10) && (Mouse.y % 25 >= 16) && (Mouse.y % 25 <= 24))//youshang
			{
				if ((Mouse.is_left()) && (Mouse.is_down()))
				{
					if (a[Mouse.y / 25 - 4][Mouse.x / 25 - 15] != 1)
					{
						putimage_transparent(NULL, white, (Mouse.x / 25 * 25 - 10), ((Mouse.y / 25 + 1) * 25 - 10), 0, 0, 0, 0, 0);
						b[Mouse.y / 25 - 4][Mouse.x / 25 - 15] = 1;
						c[0] = (Mouse.y / 25 - 4);
						c[1] = (Mouse.x / 25 - 15);
						break;
					}
				}
			}
			if ((Mouse.x % 25 >= 16) && (Mouse.x % 25 <= 24) && (Mouse.y % 25 >= 16) && (Mouse.y % 25 <= 24))//zuoshang
			{
				if ((Mouse.is_left()) && (Mouse.is_down()))
				{
					if (a[Mouse.y / 25 - 4][Mouse.x / 25 - 14] != 1)
					{
						putimage_transparent(NULL, white, ((Mouse.x / 25 + 1) * 25 - 10), ((Mouse.y / 25 + 1) * 25 - 10), 0, 0, 0, 0, 0);
						b[Mouse.y / 25 - 4][Mouse.x / 25 - 14] = 1;
						c[0] = (Mouse.y / 25 - 4);
						c[1] = (Mouse.x / 25 - 14);
						break;
					}
				}
			}
			if ((Mouse.x % 25 >= 16) && (Mouse.x % 25 <= 24) && (Mouse.y % 25 >= 0) && (Mouse.y % 25 <= 10))//zuoxia
			{
				if ((Mouse.is_left()) && (Mouse.is_down()))
				{
					if (a[Mouse.y / 25 - 5][Mouse.x / 25 - 14] != 1)
					{
						putimage_transparent(NULL, white, ((Mouse.x / 25 + 1) * 25 - 10), (Mouse.y / 25 * 25 - 10), 0, 0, 0, 0, 0);
						b[Mouse.y / 25 - 5][Mouse.x / 25 - 14] = 1;
						c[0] = (Mouse.y / 25 - 5);
						c[1] = (Mouse.x / 25 - 14);
						break;
					}
				}
			}
		}
	}
	return;
}
int winner_plo(int a[][15], int c[2])
{
	int i, j, number = 0,x,y;
	x = c[0];
	y = c[1];
	for (i = 1; i <= 4; i++)
	{
		switch (i)
		{
		case 1:	for (j = 1; j <= 4; j++)//shu lie
				{
				   if (x - j >= 0)
				   {
					   if (a[x - j][y] == 1)
						   number++;
					   else
						   break;
				   }
				}
			   for (j = 1; j <= 4; j++)
			   {
				   if (x + j <= 14)
				   {
					   if (a[x + j][y] == 1)
						   number++;
					   else
						   break;
				   }
			   }
			   break;
		case 2:	for (j = 1; j <= 4; j++)//heng hang
				{
					if (y - j >= 0)
					{
						if (a[x][y - j] == 1)
							number++;
						else
							break;
					}
				}
				for (j = 1; j <= 4; j++)
				{
					if (y + j <= 14)
					{
						if (a[x][y + j] == 1)
							number++;
						else
							break;
					}
				}
				break;
		case 3: for (j = 1; j <= 4; j++)//zuoshang dao youxia
				{
					if ((y - j >= 0) && (x - j >= 0 ))//zuoshang
					{
						if (a[x-j][y - j] == 1)
							number++;
						else
							break;
					}
				}
				for (j = 1; j <= 4; j++)
				{
					if ((y + j <= 14) && (x+j<=14))//youxia
					{
						if (a[x+j][y + j] == 1)
							number++;
						else
							break;
					}
				}
				break;
		case 4: for (j = 1; j <= 4; j++)//zuoxia dao youshang
				{
					if ((y - j >= 0) && (x+j>=0))//zuoxia
					{
						if (a[x+j][y - j] == 1)
							number++;
						else
							break;
					}
				}
				for (j = 1; j <= 4; j++)
				{
					if ((y + j <= 14) && (x-j<=14))//youshang
					{
						if (a[x-j][y + j] == 1)
							number++;
						else
							break;
					}
				}
				break;
		}
		if (number >= 4)
		{
			return 1;
		}
		else
			number = 0;
	}
	return 0;
}
int winner_plt(int b[][15], int c[2])
{
	int i, j, number = 0, x, y;
	x = c[0];
	y = c[1];
	for (i = 1; i <= 4; i++)
	{
		switch (i)
		{
		case 1:	for (j = 1; j <= 4; j++)//shu lie
		{
					if (x - j >= 0)
					{
						if (b[x - j][y] == 1)
							number++;
						else
							break;
					}
		}
				for (j = 1; j <= 4; j++)
				{
					if (x + j <= 14)
					{
						if (b[x + j][y] == 1)
							number++;
						else
							break;
					}
				}
				break;
		case 2:	for (j = 1; j <= 4; j++)//heng hang
		{
					if (y - j >= 0)
					{
						if (b[x][y - j] == 1)
							number++;
						else
							break;
					}
		}
				for (j = 1; j <= 4; j++)
				{
					if (y + j <= 14)
					{
						if (b[x][y + j] == 1)
							number++;
						else
							break;
					}
				}
				break;
		case 3: for (j = 1; j <= 4; j++)//zuoshang dao youxia
		{
					if ((y - j >= 0) && (x - j >= 0))//zuoshang
					{
						if (b[x - j][y - j] == 1)
							number++;
						else
							break;
					}
		}
				for (j = 1; j <= 4; j++)
				{
					if ((y + j <= 14) && (x + j <= 14))//youxia
					{
						if (b[x + j][y + j] == 1)
							number++;
						else
							break;
					}
				}
				break;
		case 4: for (j = 1; j <= 4; j++)//zuoxia dao youshang
		{
					if ((y - j >= 0) && (x + j >= 0))//zuoxia
					{
						if (b[x + j][y - j] == 1)
							number++;
						else
							break;
					}
		}
				for (j = 1; j <= 4; j++)
				{
					if ((y + j <= 14) && (x - j <= 14))//youshang
					{
						if (b[x - j][y + j] == 1)
							number++;
						else
							break;
					}
				}
				break;
		}
		if (number >= 4)
		{
			return 1;
		}
		else
			number = 0;
	}
	return 0;
}
void Findmax(long score[15][15][2], int chessboard[15][15], int coo[2])
{

	int i, j, n, max_one = 0, max_two = 0, site[4];
	for (n = 0; n < 2; n++)
	{
		if (n == 0)
		{
			for (i = 0; i < 15; i++)
			{
				for (j = 0; j < 15; j++)
				{
					if (chessboard[i][j] == 0)
					{
						if (score[i][j][n]>max_one)
						{
							max_one = score[i][j][n];
							site[0] = i;
							site[1] = j;
						}
					}
				}
			}
		}
		if (n == 1)
		{
			for (i = 0; i < 15; i++)
			{
				for (j = 0; j < 15; j++)
				{
					if (chessboard[i][j] == 0)
					{
						if (score[i][j][n]>max_two)
						{
							max_two = score[i][j][n];
							site[2] = i;
							site[3] = j;
						}
					}
				}
			}
		}
	}
	if (max_one > max_two)
	{
		coo[0] = site[0];
		coo[1] = site[1];
	}
	else
	{
		coo[0] = site[2];
		coo[1] = site[3];
	}
	return;
}
void analyze(int chessboard[15][15], long score[15][15][2])
{
	int i, j, m = 0, n = 0, h, number[4] = { 0 }, com[4] = { 0 };
	char ceshi[80];
	for (n = 0; n < 2; n++)
	{
		if (n == 0)
		{
			for (i = 0; i < 15; i++)
			{
				for (j = 0; j < 15; j++)
				{
					if (chessboard[i][j] == 0)
					{
						for (m = 0; m < 8; m++)
						{
							if (m == 0)//zuoce
							{
								for (h = 1; h < 5; h++)
								{
									if ((j - h) >= 0)
									{
										if (chessboard[i][j - h] == 1)
											number[0]++;
										else
											break;
									}
								}
							}
							if (m == 1)//youce
							{
								for (h = 1; h < 5; h++)
								{
									if ((j + h) <= 14)
									{
										if (chessboard[i][j + h] == 1)
											number[0]++;
										else
											break;
									}
								}
							}
							if (m == 2)//shangfang
							{
								for (h = 1; h < 5; h++)
								{
									if ((i - h) >= 0)
									{
										if (chessboard[i - h][j] == 1)
											number[1]++;
										else
											break;
									}
								}
							}
							if (m == 3)//xiafang
							{
								for (h = 1; h < 5; h++)
								{
									if ((i + h) <= 14)
									{
										if (chessboard[i + h][j] == 1)
											number[1]++;
										else
											break;
									}
								}
							}
							if (m == 4)//zuoshang
							{
								for (h = 1; h < 5; h++)
								{
									if (((i - h) >= 0) && ((j - h) >= 0))
									{
										if (chessboard[i - h][j - h] == 1)
											number[2]++;
										else
											break;
									}
								}
							}
							if (m == 5)//youxia
							{
								for (h = 1; h < 5; h++)
								{
									if (((i + h) <= 14) && ((j + h) <= 14))
									{
										if (chessboard[i + h][j + h] == 1)
											number[2]++;
										else
											break;
									}
								}
							}
							if (m == 6)//zuoxia
							{
								for (h = 1; h < 5; h++)
								{
									if (((i + h) <= 14) && ((j - h) >= 0))
									{
										if (chessboard[i + h][j - h] == 1)
											number[3]++;
										else
											break;
									}
								}
							}
							if (m == 7)//youshang
							{
								for (h = 1; h < 5; h++)
								{
									if (((i - h) >= 0) && ((j + h) <= 14))
									{
										if (chessboard[i - h][j + h] == 1)
											number[3]++;
										else
											break;
									}
								}
							}
						}
					}
					for (h = 0; h < 4; h++)
					{
						if (number[h] >= 4)
							score[i][j][n] += 100000;
						if (number[h] == 3)
							score[i][j][n] += 10000;
						if (number[h] == 2)
							score[i][j][n] += 1000;
						if (number[h] == 1)
							score[i][j][n] += 100;
					}
					for (h = 0; h < 4; h++)
					{
						number[h] = 0;
					}

				}
			}
		}
		if (n == 1)
		{
			for (i = 0; i < 15; i++)
			{
				for (j = 0; j < 15; j++)
				{
					if (chessboard[i][j] == 0)
					{
						for (m = 0; m < 8; m++)
						{
							if (m == 0)//zuoce
							{
								for (h = 1; h < 5; h++)
								{
									if ((j - h) >= 0)
									{
										if (chessboard[i][j - h] == 2)
											com[0]++;
										else
											break;
									}
								}
							}
							if (m == 1)//youce
							{
								for (h = 1; h < 5; h++)
								{
									if ((j + h) <= 14)
									{
										if (chessboard[i][j + h] == 2)
											com[0]++;
										else
											break;
									}
								}
							}
							if (m == 2)//shangfang
							{
								for (h = 1; h < 5; h++)
								{
									if ((i - h) >= 0)
									{
										if (chessboard[i - h][j] == 2)
											com[1]++;
										else
											break;
									}
								}
							}
							if (m == 3)//xiafang
							{
								for (h = 1; h < 5; h++)
								{
									if ((i + h) <= 14)
									{
										if (chessboard[i + h][j] == 2)
											com[1]++;
										else
											break;
									}
								}
							}
							if (m == 4)//zuoshang
							{
								for (h = 1; h < 5; h++)
								{
									if (((i - h) >= 0) && ((j - h) >= 0))
									{
										if (chessboard[i - h][j - h] == 2)
											com[2]++;
										else
											break;
									}
								}
							}
							if (m == 5)//youxia
							{
								for (h = 1; h < 5; h++)
								{
									if (((i + h) <= 14) && ((j + h) <= 14))
									{
										if (chessboard[i + h][j + h] == 2)
											com[2]++;
										else
											break;
									}
								}
							}
							if (m == 6)//zuoxia
							{
								for (h = 1; h < 5; h++)
								{
									if (((i + h) <= 14) && ((j - h) >= 0))
									{
										if (chessboard[i + h][j - h] == 2)
											com[3]++;
										else
											break;
									}
								}
							}
							if (m == 7)//youshang
							{
								for (h = 1; h < 5; h++)
								{
									if (((i - h) >= 0) && ((j + h) <= 14))
									{
										if (chessboard[i - h][j + h] == 2)
											com[3]++;
										else
											break;
									}
								}
							}
						}
					}
					for (h = 0; h < 4; h++)
					{
						if (com[h] >= 4)
							score[i][j][n] += 100000;
						if (com[h] == 3)
							score[i][j][n] += 10000;
						if (com[h] == 2)
							score[i][j][n] += 1000;
						if (com[h] == 1)
							score[i][j][n] += 100;
					}
					for (h = 0; h < 4; h++)
					{
						com[h] = 0;
					}
				}
			}
		}
	}
	return;
}
void play(int chessboard[15][15],int c[2])
{
	mouse_msg Mouse = { 0 };
	while (1)
	{
		Mouse = getmouse();
		if (Mouse.is_move())
			mousepos(&Mouse.x, &Mouse.y);
		if (((Mouse.x) >= 369) && ((Mouse.x) <= 736) && ((Mouse.y) >= 119) && ((Mouse.y) <= 486))
		{
			if ((Mouse.x % 25 >= 0) && (Mouse.x % 25 <= 10) && (Mouse.y % 25 >= 0) && (Mouse.y % 25 <= 10))//youxia
			{
				if ((Mouse.is_left()) && (Mouse.is_down()))
				{
					if (chessboard[Mouse.y / 25 - 5][Mouse.x / 25 - 15] == 0)
					{
						putimage_transparent(NULL, black, (Mouse.x / 25 * 25 - 10), (Mouse.y / 25 * 25 - 10), 0, 0, 0, 0, 0);
						chessboard[Mouse.y / 25 - 5][Mouse.x / 25 - 15] = 1;
						c[0] = (Mouse.y / 25 - 5);
						c[1] = (Mouse.x / 25 - 15);
						break;
					}
				}
			}
			if ((Mouse.x % 25 >= 0) && (Mouse.x % 25 <= 10) && (Mouse.y % 25 >= 16) && (Mouse.y % 25 <= 24))//youshang
			{
				if ((Mouse.is_left()) && (Mouse.is_down()))
				{
					if (chessboard[Mouse.y / 25 - 4][Mouse.x / 25 - 15] == 0)
					{
						putimage_transparent(NULL, black, (Mouse.x / 25 * 25 - 10), ((Mouse.y / 25 + 1) * 25 - 10), 0, 0, 0, 0, 0);
						chessboard[Mouse.y / 25 - 4][Mouse.x / 25 - 15] = 1;
						c[0] = (Mouse.y / 25 - 4);
						c[1] = (Mouse.x / 25 - 15);
						break;
					}
				}
			}
			if ((Mouse.x % 25 >= 16) && (Mouse.x % 25 <= 24) && (Mouse.y % 25 >= 16) && (Mouse.y % 25 <= 24))//zuoshang
			{
				if ((Mouse.is_left()) && (Mouse.is_down()))
				{
					if (chessboard[Mouse.y / 25 - 4][Mouse.x / 25 - 14] == 0)
					{
						putimage_transparent(NULL, black, ((Mouse.x / 25 + 1) * 25 - 10), ((Mouse.y / 25 + 1) * 25 - 10), 0, 0, 0, 0, 0);
						chessboard[Mouse.y / 25 - 4][Mouse.x / 25 - 14] = 1;
						c[0] = (Mouse.y / 25 - 4);
						c[1] = (Mouse.x / 25 - 14);
						break;
					}
				}
			}
			if ((Mouse.x % 25 >= 16) && (Mouse.x % 25 <= 24) && (Mouse.y % 25 >= 0) && (Mouse.y % 25 <= 10))//zuoxia
			{
				if ((Mouse.is_left()) && (Mouse.is_down()))
				{
					if (chessboard[Mouse.y / 25 - 5][Mouse.x / 25 - 14] == 0)
					{
						putimage_transparent(NULL, black, ((Mouse.x / 25 + 1) * 25 - 10), (Mouse.y / 25 * 25 - 10), 0, 0, 0, 0, 0);
						chessboard[Mouse.y / 25 - 5][Mouse.x / 25 - 14] = 1;
						c[0] = (Mouse.y / 25 - 5);
						c[1] = (Mouse.x / 25 - 14);
						break;
					}
				}
			}
		}
	}
	return;
}
int winner(int c[2],int n,int chessboard[15][15])
{
	int i, j,x,y,number=0;
	x = c[0];
	y = c[1];
	if (n == 0)
	{
		for (i = 1; i < 5; i++)
		{
			switch (i)
			{
			case 1:	for (j = 1; j < 5; j++)//zuoce
					{
						if ((y - j) >= 0)
						{
							if (chessboard[x][y-j] == 1)
								number++;
							else
								break;
						}
					}
					for (j = 1; j < 5; j++)//youce
					{
						if ((y + j) <= 14)
						{
							if (chessboard[x][y+j] == 1)
								number++;
							else
								break;
						}
					}
					break;
			case 2:	for (j = 1; j < 5; j++)//shangfang
					{
						if ((x - j) >= 0)
						{
							if (chessboard[x-j][y] == 1)
								number++;
							else
								break;
						}
					}
					for (j = 1; j < 5; j++)//xiafang
					{
						if ((x + j) <= 14)
						{
							if (chessboard[x+j][y] == 1)
								number++;
							else
								break;
						}
					}
					break;
			case 3:	for (j = 1; j < 5; j++)//zuoshang
					{
						if (((x - j) >= 0)&&((y-j)>=0))
						{
							if (chessboard[x-j][y-j] == 1)
								number++;
							else
								break;
						}
					}
					for (j = 1; j < 5; j++)//youxia
					{
						if (((x + j) <= 14)&&((y+j)<=14))
						{
							if (chessboard[x+j][y+j] == 1)
								number++;
							else
								break;
						}
					}
					break;
			case 4:	for (j = 1; j < 5; j++)//youshang
					{
						if (((x - j) >= 0) && ((y + j) <= 14))
						{
							if (chessboard[x - j][y + j] == 1)
								number++;
							else
								break;
						}
					}
					for (j = 1; j < 5; j++)//zuoxia
					{
						if (((x + j) <= 14) && ((y - j) >= 0))
						{
							if (chessboard[x + j][y - j] == 1)
								number++;
							else
								break;
						}
					}
					break;
			}
			if (number >= 4)
				return 1;
			else
				number = 0;
		}
	}
	if (n == 1)
	{
		for (i = 1; i < 5; i++)
		{
			switch (i)
			{
			case 1:	for (j = 1; j < 5; j++)//zuoce
					{
						if ((y - j) >= 0)
						{
							if (chessboard[x][y-j] == 2)
								number++;
							else
								break;
						}
					}
					for (j = 1; j < 5; j++)//youce
					{
						if ((y + j) <= 14)
						{
							if (chessboard[x][y+j] == 2)
								number++;
							else
								break;
						}
					}
					break;
			case 2:	for (j = 1; j < 5; j++)//shangfang
					{
						if ((x - j) >= 0)
						{
							if (chessboard[x-j][y] == 2)
								number++;
							else
								break;
						}
					}
					for (j = 1; j < 5; j++)//xiafang
					{
						if ((x + j) <= 14)
						{
							if (chessboard[x+j][y] == 2)
								number++;
							else
								break;
						}
					}
					break;
			case 3:	for (j = 1; j < 5; j++)//zuoshang
					{
						if (((x - j) >= 0) && ((y - j) >= 0))
						{
							if (chessboard[x - j][y - j] == 2)
								number++;
							else
								break;
						}
					}
					for (j = 1; j < 5; j++)//youxia
					{
						if (((x + j) <= 14) && ((y + j) <= 14))
						{
							if (chessboard[x + j][y + j] == 2)
								number++;
							else
								break;
						}
					}
					break;
			case 4:	for (j = 1; j < 5; j++)//youshang
					{
						if (((x - j) >= 0) && ((y + j) <= 14))
						{
							if (chessboard[x - j][y + j] == 2)
								number++;
							else
								break;
						}
					}
					for (j = 1; j < 5; j++)//zuoxia
					{
						if (((x + j) <= 14) && ((y - j) >= 0))
						{
							if (chessboard[x + j][y - j] == 2)
								number++;
							else
								break;
						}
					}
					break;
			}
			if (number >= 4)
				return 1;
			else
				number = 0;
		}
	}
	return 0;
}
void Rungame(int x)
{
	int result = 0, i, j, m, n, plo[15][15] = { 0 }, plt[15][15] = { 0 }, chessboard[15][15] = { 0 }, fir = 0, sec = 0, c[2] = { 0 }, d[2] = { 0 };
	long score[15][15][2] = { 0 };
	char ceshi[50];
	initgraph(800, 600);
	A:result = draw_choice(1);
	draw_chess(result);
	if (result == 1)
	{
		for (i = 1; i <= 225; i++)
		{
			play(chessboard, c);
			fir = winner(c, 0, chessboard);
			if (fir == 1)
			{
				putimage_transparent(NULL, vic_b, -200, -150, 0, 0, 0, 0, 0);
				for (j = 0; j < 15; j++)
				{
					for (m = 0; m < 15; m++)
					{
						chessboard[j][m] = 0;
						score[j][m][0] = 0;
						score[j][m][1] = 0;
						fir = 0;
					}
				}
				getch();
				break;
			}
			analyze(chessboard, score);
			Findmax(score, chessboard, c);
			chessboard[c[0]][c[1]] = 2;
			Sleep(200);
			putimage_transparent(NULL, white, ((c[1] + 1) * 25 + 340), ((c[0] + 1) * 25 + 90), 0, 0, 0, 0, 0);
			for (j = 0; j < 15; j++)
			{
				for (m = 0; m < 15; m++)
				{
					for (n = 0; n < 2; n++)
					{
						score[j][m][n] = 0;
					}
				}
			}
			sec = winner(c, 1, chessboard);
			if (sec == 1)
			{
				putimage_transparent(NULL, vic_w, -200, -150, 0, 0, 0, 0, 0);
				for (j = 0; j < 15; j++)
				{
					for (m = 0; m < 15; m++)
					{
						chessboard[j][m] = 0;
						score[j][m][0] = 0;
						score[j][m][1] = 0;
						sec = 0;
					}
				}
				getch();
				break;
			}
			if (i == 225)
				putimage_transparent(NULL, vic_null, 0, 0, 0, 0, 0, 0, 0);
		}
	}
	if (result == 2)
	{

		for (i = 1; i <= 225; i++)
		{
			player_one(plo, plt, c);
			fir = winner_plo(plo, c);
			if (fir == 1)
			{
				fir = 0;
				putimage_transparent(NULL, vic_b, -200, -150, 0, 0, 0, 0, 0);
				for (j = 0; j < 15; j++)
				{
					for (m = 0; m < 15; m++)
					{
						plo[j][m] = 0;
						plt[j][m] = 0;
						if (i < 2)
						{
							c[i] = 0;
						}
					}
				}

				break;
			}
			player_two(plo, plt, c);
			sec = winner_plt(plt, c);
			if (sec == 1)
			{
				sec = 0;
				putimage_transparent(NULL, vic_w, -200, -150, 0, 0, 0, 0, 0);
				for (j = 0; j < 15; j++)
				{
					for (m = 0; m < 15; m++)
					{
						plo[j][m] = 0;
						plt[j][m] = 0;
						if (i < 2)
						{
							c[i] = 0;
						}
					}
				}
				break;
			}
		}
		if (i == 225)
			putimage_transparent(NULL, vic_null, 0, 0, 0, 0, 0, 0, 0);
		getch();
		putimage(0, 0, chess);
	}
	if (result == 0)
	{
		closegraph();
	}
	goto A;
	delimage(chess);
	delimage(black);
	delimage(white);
	delimage(vic_b);
	delimage(vic_w);
	delimage(vic_null);
	return ;

}



