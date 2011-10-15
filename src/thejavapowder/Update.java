package thejavapowder;

import java.util.Random;

@SuppressWarnings("static-access")
public class Update {
    /*Update class
      * Updates variables
      *
      * Known Bugs to fix:
      *
      * Particles react with other particles that are well over them ( Maybe Down left and right too, not tested )
      * Fix: Unknown
      *
      *
      *
      *
      *
      */

    long StartTime = 0;
    long EndTime = 0;
    long TotalFPS = 0;
    int TotalFrame = 0;


    byte FPS;
    float Time = 1000;


    Random rand = new Random();
    Variables var = new Variables();
    Methods meth = new Methods();
    //Booleans and stuff

    Object obj;
    //Order of creation: new Element(amount-of-burn,weight,conductive(boolean),state("g","p","s","l"), name, descripting, colour(HEX), react ( Array ))



    @SuppressWarnings("static-access")
    public void update() {
        EndTime = System.currentTimeMillis();

        Time = EndTime - StartTime;
        FPS = (byte) (1000 / Time);
        TotalFPS += FPS;
        TotalFrame++;

        StartTime = System.currentTimeMillis();

        if (TotalFrame > 30) {
            TotalFrame = 1;
            TotalFPS = FPS;
        }

        if (var.Simulating && var.state == 0) {
            for (int x = var.Width - 1; x > 1; x--) {
                for (int y = var.Height - 1; y > 1; y--)//For each Space
                {
                    if (var.Map[x][y] != -127) {

                        if (var.currentMode == 1 && meth.GetConductive(var.Map[x][y]))//If in electronic mode
                        {
                            UpdateVoltage(x, y);
                        }


                        if (var.currentMode == 0)//Reactive Engine
                        {
                            meth.getReactives(var.Map[x][y]);
                            meth.getSurroundings(x, y);

                            if (var.reactives != null) {
                                for (int o = 0; o < var.reactives.length; o++)//For the number of reactive the particle have
                                {
                                    if (var.stopReactions)//If the particle already reacted
                                    {
                                        this.var.stopReactions = false;
                                        break;//Stop it
                                    }
                                    for (int i = 0; i < 8; i++)//If every space around the particle
                                    {

                                        if (var.stopReactions)//If the particle already reacted
                                        {
                                            break;//Stop it
                                        }
                                        if (var.surArray[i] == var.reactives[o])//If the tested space is one of the var.reactives
                                        {
                                            var.stopReactions = true;//Stop looping

                                            switch (i) {
                                                case 0:
                                                    meth.getReaction(var.Map[x][y], var.Map[x - 1][y - 1]);//Get the Reaction needed
                                                    var.Map[x][y] = var.reaction[0];

                                                    if (var.reaction[1] == 1) {
                                                        var.Map[x - 1][y - 1] = -127;
                                                    }

                                                    var.VMap[x][y] += var.reaction[2];
                                                    var.PMap[x][y] = var.reaction[3];
                                                    var.HMap[x][y] += var.reaction[4];
                                                    //PresMap[x][y] += var.reaction[5];

                                                    if (var.reaction[6] != var.Map[x][y])//If you're not creating what the particle is
                                                    {
                                                        meth.createParticle(x, y, var.reaction[6]);//Create it
                                                    }

                                                    break;
                                                case 1:
                                                    meth.getReaction(var.Map[x][y], var.Map[x][y - 1]);//Get the Reaction needed
                                                    var.Map[x][y] = var.reaction[0];

                                                    if (var.reaction[1] == 1) {
                                                        var.Map[x][y - 1] = -127;
                                                    }

                                                    var.VMap[x][y] += var.reaction[2];
                                                    var.PMap[x][y] = var.reaction[3];
                                                    var.HMap[x][y] += var.reaction[4];
                                                    //PresMap[x][y] += var.reaction[5];

                                                    if (var.reaction[6] != var.Map[x][y]) {
                                                        meth.createParticle(x, y, var.reaction[6]);
                                                    }

                                                    break;
                                                case 2:
                                                    meth.getReaction(var.Map[x][y], var.Map[x + 1][y - 1]);//Get the Reaction needed
                                                    var.Map[x][y] = var.reaction[0];

                                                    if (var.reaction[1] == 1) {
                                                        var.Map[x + 1][y - 1] = -127;
                                                    }

                                                    var.VMap[x][y] += var.reaction[2];
                                                    var.PMap[x][y] = var.reaction[3];
                                                    var.HMap[x][y] += var.reaction[4];
                                                    //PresMap[x][y] += var.reaction[5];

                                                    if (var.reaction[6] != var.Map[x][y]) {
                                                        meth.createParticle(x, y, var.reaction[6]);
                                                    }

                                                    break;
                                                case 3:
                                                    meth.getReaction(var.Map[x][y], var.Map[x - 1][y]);//Get the Reaction needed
                                                    var.Map[x][y] = var.reaction[0];

                                                    if (var.reaction[1] == 1) {
                                                        var.Map[x - 1][y] = -127;
                                                    }

                                                    var.VMap[x][y] += var.reaction[2];
                                                    var.PMap[x][y] = var.reaction[3];
                                                    var.HMap[x][y] += var.reaction[4];
                                                    //PresMap[x][y] += var.reaction[5];

                                                    if (var.reaction[6] != var.Map[x][y]) {
                                                        meth.createParticle(x, y, var.reaction[6]);
                                                    }

                                                    break;
                                                case 4:
                                                    meth.getReaction(var.Map[x][y], var.Map[x + 1][y]);//Get the Reaction needed
                                                    var.Map[x][y] = var.reaction[0];

                                                    if (var.reaction[1] == 1) {
                                                        var.Map[x + 1][y] = -127;
                                                    }

                                                    var.VMap[x][y] += var.reaction[2];
                                                    var.PMap[x][y] = var.reaction[3];
                                                    var.HMap[x][y] += var.reaction[4];
                                                    //PresMap[x][y] += var.reaction[5];

                                                    if (var.reaction[6] != var.Map[x][y]) {
                                                        meth.createParticle(x, y, var.reaction[6]);
                                                    }

                                                    break;
                                                case 5:
                                                    meth.getReaction(var.Map[x][y], var.Map[x - 1][y + 1]);//Get the Reaction needed
                                                    var.Map[x][y] = var.reaction[0];

                                                    if (var.reaction[1] == 1) {
                                                        var.Map[x - 1][y + 1] = -127;
                                                    }

                                                    var.VMap[x][y] += var.reaction[2];
                                                    var.PMap[x][y] = var.reaction[3];
                                                    var.HMap[x][y] += var.reaction[4];
                                                    //PresMap[x][y] += var.reaction[5];

                                                    if (var.reaction[6] != var.Map[x][y]) {
                                                        meth.createParticle(x, y, var.reaction[6]);
                                                    }

                                                    break;
                                                case 6:
                                                    meth.getReaction(var.Map[x][y], var.Map[x][y + 1]);//Get the Reaction needed
                                                    var.Map[x][y] = var.reaction[0];

                                                    if (var.reaction[1] == 1) {
                                                        var.Map[x][y + 1] = -127;
                                                    }

                                                    var.VMap[x][y] += var.reaction[2];
                                                    var.PMap[x][y] = var.reaction[3];
                                                    var.HMap[x][y] += var.reaction[4];
                                                    //PresMap[x][y] += var.reaction[5];

                                                    if (var.reaction[6] != var.Map[x][y]) {
                                                        meth.createParticle(x, y, var.reaction[6]);
                                                    }

                                                    break;
                                                case 7:
                                                    meth.getReaction(var.Map[x][y], var.Map[x + 1][y + 1]);//Get the Reaction needed
                                                    var.Map[x][y] = var.reaction[0];

                                                    if (var.reaction[1] == 1) {
                                                        var.Map[x + 1][y + 1] = -127;
                                                    }

                                                    var.VMap[x][y] += var.reaction[2];
                                                    var.PMap[x][y] = var.reaction[3];
                                                    var.HMap[x][y] += var.reaction[4];
                                                    //PresMap[x][y] += var.reaction[5];

                                                    if (var.reaction[6] != var.Map[x][y]) {
                                                        meth.createParticle(x, y, var.reaction[6]);
                                                    }
                                                    break;

                                            }
                                        }
                                    }
                                }
                            }
                            if (meth.getType(x, y) == 'p')//If it's a powder
                            {
                                UpdatePowder(x, y);
                            }//End of powders


                            if (meth.getType(x, y) == 'l')//If it's a Liquid
                            {
                                UpdateLiquids(x, y);
                            }//End of Liquids


                            if (meth.getType(x, y) == 'g')//If it's a gas
                            {
                                UpdateGasses(x, y);
                            }//End of gases
                        }
                    }
                }
            }
            if (var.active) {
                if (var.Drawing) {
                    create_line(var.DrawX, var.DrawY, var.LastDrawX, var.LastDrawY, var.Size, var.Equipped);
                }
            } else {
                if (var.wait < 1) {
                    var.active = true;
                    var.wait = 30;
                } else {
                    var.wait--;
                }
            }
            var.LastDrawX = var.DrawX;
            var.LastDrawY = var.DrawY;
        }//End of Updating maps


        for (int a = 0; a < var.Width; a++) {
            for (int b = 0; b < var.Height; b++)//For each Tile of the Boolean var.Map
            {
                var.BMap[a][b] = false;//Make it false so particles can move next turn
            }
        }


    }//End of Update

    public void drawPoint(int x, int y, byte id) {
        if (var.active) {
            var.wait = 30;
            if (id != -126 && id != -125) {
                if (var.leftClick) {
                    if (x > 1 && y > 1 && x < var.Width && y < var.Height && var.Map[x][y] == -127)//If the target tile is free
                    {
                        var.Map[x][y] = id;
                    }
                } else {
                    if (x > 1 && y > 1 && x < var.Width && y < var.Height && var.Map[x][y] != -127)//If the target tile is not free
                    {
                        var.Map[x][y] = -127;//Clean the Map
                        var.VMap[x][y] = 0;//Clean the VMap
                        var.PMap[x][y] = 0;//Clean the PMap
                    }
                }
            } else if (id == -126) {
                if (var.leftClick) {
                    if (x > 1 && y > 1 && x < var.Width && y < var.Height && (var.Map[x][y] == 4 || var.Map[x][y] == 6)) {
                        var.VMap[x][y] += 5;
                        if (var.VMap[x][y] > 1500)
                            var.VMap[x][y] = 1500;
                    }
                } else {
                    if (x > 1 && y > 1 && x < var.Width && y < var.Height && var.Map[x][y] != 0) {
                        var.Map[x][y] = -127;
                        var.VMap[x][y] = 0;
                        var.PMap[x][y] = 0;
                    }
                }
            } else {
                if (var.leftClick) {
                    if (var.PMap[x][y] < 50) {
                        if (var.Map[x][y] == 11 || var.Map[x][y] == 10 || var.Map[x][y] == 14) {
                            var.PMap[x][y] += 1;
                        }
                    }
                } else {
                    if (var.PMap[x][y] > 1) {
                        if (var.Map[x][y] == 11 || var.Map[x][y] == 10 || var.Map[x][y] == 14) {
                            var.PMap[x][y] -= 1;
                        }
                    }
                }
            }
        } else {
            var.wait--;
            if (var.wait < 1) {
                var.active = true;
            }
        }
    }

    @SuppressWarnings("static-access")
    public void drawCircle(int x, int y, byte rd, byte id) {
        int tempy = y;
        for (int i = x - rd; i <= x; i++) {
            double distance = Math.sqrt(Math.pow((double) x - (double) i, (double) 2) + Math.pow((double) y - (double) tempy, (double) 2));
            while (distance <= rd) {
                tempy = tempy - 1;
                distance = Math.sqrt(Math.pow((double) x - (double) i, (double) 2) + Math.pow((double) y - (double) tempy, (double) 2));
            }
            tempy = tempy + 1;
            for (int j = tempy; j <= 2 * y - tempy; j++) {
                if (i > 0 && j > 0 && i < var.Width * var.winZoom && j < var.Height * var.winZoom)
                    drawPoint(i, j, id);
                if (2 * x - i > 0 && j > 0 && 2 * x - i < var.Width * var.winZoom && j < var.Height * var.winZoom)
                    drawPoint(2 * x - i, j, id);
            }
        }
    }

    @SuppressWarnings("static-access")
    public void drawSquare(int xc, int yc, byte size, byte id) {
        for (int x = xc; x < xc + size; x++) {
            for (int y = yc; y < yc + size; y++) {
                drawPoint(x, y, id);
            }
        }
        if (size == 0)
            drawPoint(xc, yc, id);
    }

    @SuppressWarnings("static-access")
    public void draw(int x, int y, byte s, byte i) {
        if (var.Shape == 0)
            drawSquare(x - s, y - s, (byte) (s * 2), i);
        else
            drawCircle(x, y, s, i);
    }

    @SuppressWarnings("static-access")
    public void create_line(int x1, int y1, int x2, int y2, byte rd, byte id) // From old autorun.lua
    {
        if (x1 > x2) {
            int xc2 = x1;
            x1 = x2;
            x2 = xc2;
            int yc2 = y1;
            y1 = y2;
            y2 = yc2;
        }
        draw(x1, y1, rd, id);
        if (x1 == x2) {
            if (y1 != y2) {
                int yc1 = Math.min(y1, y2);
                int yc2 = Math.max(y1, y2);
                for (int i = yc1; i < yc2; i++)
                    draw(x1, i, rd, id);
            }
            return;
        }
        double slope = (double) (y2 - y1) / (x2 - x1);
        for (int i = x1; i < x2; i++) {
            double yc1 = y1 + slope * (i - x1);
            double yc2 = y1 + slope * (i + 1 - x1);
            int left = (int) Math.floor(Math.min(yc1, yc2));
            int right = (int) Math.ceil(Math.max(yc1, yc2));
            if (i == x2) right = left;
            for (int j = left; j <= right; j++)
                draw(i, j, rd, id);
        }
    }

    @SuppressWarnings("static-access")
    public void UpdateVoltage(int x, int y) {
        if (var.Map[x][y] == 5)//If it's a battery, give it infinite voltage
        {
            var.VMap[x][y] = 1000;
        }

        if (var.VMap[x][y] > 1)//If there's Voltage
        {
            if (var.Map[x][y] == 4)//Iron
            {
                if (meth.GetConductive(var.Map[x][y + 1]))//If the particle down is conductive
                {
                    if (var.Map[x][y + 1] != 11 || var.Map[x][y + 1] != 13)//If it's not a rechargable battery or crossing
                    {
                        if (var.VMap[x][y + 1] < var.VMap[x][y]) {
                            var.VMap[x][y] -= 3;
                            var.VMap[x][y + 1] = var.VMap[x][y];
                        }
                    }
                    if (var.Map[x][y + 1] == 11) {
                        var.VMap[x][y] -= 20;
                        var.VMap[x][y + 1] += 20;
                    }
                    if (var.Map[x][y + 1] == 13)//If it's crossing
                    {
                        if (meth.GetConductive(var.Map[x][y + 2])) {
                            var.VMap[x][y] -= 3;
                            var.VMap[x][y + 2] = var.VMap[x][y];
                        } else if (var.VMap[x][y + 2] == 13) {
                            if (meth.GetConductive(var.Map[x][y + 3])) {
                                var.VMap[x][y] -= 3;
                                var.VMap[x][y + 3] = var.VMap[x][y];
                            } else if (var.VMap[x][y + 3] == 13) {
                                if (meth.GetConductive(var.Map[x][y + 4])) {
                                    var.VMap[x][y] -= 3;
                                    var.VMap[x][y + 4] = var.VMap[x][y];
                                } else if (var.VMap[x][y + 4] == 13) {
                                    if (meth.GetConductive(var.Map[x][y + 5])) {
                                        var.VMap[x][y] -= 3;
                                        var.VMap[x][y + 5] = var.VMap[x][y];
                                    }
                                }
                            }
                        }
                    }
                }
                if (meth.GetConductive(var.Map[x][y - 1]))//If the particle up is conductive
                {
                    if (var.Map[x][y - 1] != 11 || var.Map[x][y - 1] != 13)//If it's not a rechargable battery or crossing
                    {
                        if (var.VMap[x][y - 1] < var.VMap[x][y]) {
                            var.VMap[x][y] -= 3;
                            var.VMap[x][y - 1] = var.VMap[x][y];
                        }
                    }
                    if (var.Map[x][y - 1] == 13)//If it's crossing
                    {
                        if (meth.GetConductive(var.Map[x][y - 2])) {
                            var.VMap[x][y] -= 3;
                            var.VMap[x][y - 2] = var.VMap[x][y];
                        } else if (var.VMap[x][y - 2] == 13) {
                            if (meth.GetConductive(var.Map[x][y - 3])) {
                                var.VMap[x][y] -= 3;
                                var.VMap[x][y - 3] = var.VMap[x][y];
                            } else if (var.VMap[x][y - 3] == 13) {
                                if (meth.GetConductive(var.Map[x][y - 4])) {
                                    var.VMap[x][y] -= 3;
                                    var.VMap[x][y - 4] = var.VMap[x][y];
                                } else if (var.VMap[x][y - 4] == 13) {
                                    if (meth.GetConductive(var.Map[x][y - 5])) {
                                        var.VMap[x][y] -= 3;
                                        var.VMap[x][y - 5] = var.VMap[x][y];
                                    }
                                }
                            }
                        }
                    }
                }
                if (meth.GetConductive(var.Map[x + 1][y]))//If the particle right is conductive
                {
                    if (var.Map[x + 1][y] != 11 || var.Map[x + 1][y] != 13)//If it's not a rechargable battery or crossing
                    {
                        if (var.VMap[x + 1][y] < var.VMap[x][y]) {
                            var.VMap[x][y] -= 3;
                            var.VMap[x + 1][y] = var.VMap[x][y];
                        }
                    }
                    if (var.Map[x + 1][y] == 11) {
                        var.VMap[x][y] -= 20;
                        var.VMap[x + 1][y] += 20;
                    }
                    if (var.Map[x + 1][y] == 13)//If it's crossing
                    {
                        if (meth.GetConductive(var.Map[x + 2][y])) {
                            var.VMap[x][y] -= 3;
                            var.VMap[x + 2][y] = var.VMap[x][y];
                        } else if (var.VMap[x + 2][y] == 13) {
                            if (meth.GetConductive(var.Map[x + 3][y])) {
                                var.VMap[x][y] -= 3;
                                var.VMap[x + 3][y] = var.VMap[x][y];
                            } else if (var.VMap[x + 3][y] == 13) {
                                if (meth.GetConductive(var.Map[x + 4][y])) {
                                    var.VMap[x][y] -= 3;
                                    var.VMap[x + 4][y] = var.VMap[x][y];
                                } else if (var.VMap[x + 4][y] == 13) {
                                    if (meth.GetConductive(var.Map[x + 5][y])) {
                                        var.VMap[x][y] -= 3;
                                        var.VMap[x + 5][y] = var.VMap[x][y];
                                    }
                                }
                            }
                        }
                    }
                }
                if (meth.GetConductive(var.Map[x - 1][y]))//If the particle left is conductive
                {
                    if (var.Map[x - 1][y] != 11 || var.Map[x - 1][y] != 13)//If it's not a rechargable battery or crossing
                    {
                        if (var.VMap[x - 1][y] < var.VMap[x][y]) {
                            var.VMap[x][y] -= 3;
                            var.VMap[x - 1][y] = var.VMap[x][y];
                        }
                    }
                    if (var.Map[x - 1][y] == 11) {
                        var.VMap[x][y] -= 20;
                        var.VMap[x - 1][y] += 20;
                    }
                    if (var.Map[x - 1][y] == 13)//If it's crossing
                    {
                        if (meth.GetConductive(var.Map[x - 2][y])) {
                            var.VMap[x][y] -= 3;
                            var.VMap[x - 2][y] = var.VMap[x][y];
                        } else if (var.VMap[x = 2][y] == 13) {
                            if (meth.GetConductive(var.Map[x - 3][y])) {
                                var.VMap[x][y] -= 3;
                                var.VMap[x - 3][y] = var.VMap[x][y];
                            } else if (var.VMap[x - 3][y] == 13) {
                                if (meth.GetConductive(var.Map[x - 4][y])) {
                                    var.VMap[x][y] -= 3;
                                    var.VMap[x - 4][y] = var.VMap[x][y];
                                } else if (var.VMap[x - 4][y] == 13) {
                                    if (meth.GetConductive(var.Map[x - 5][y])) {
                                        var.VMap[x][y] -= 3;
                                        var.VMap[x - 5][y] = var.VMap[x][y];
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (var.Map[x][y] == 5)//Battery
            {
                if (meth.GetConductive(var.Map[x][y + 1])) {
                    var.VMap[x][y + 1] = var.VMap[x][y];

                }
                if (meth.GetConductive(var.Map[x][y - 1])) {
                    var.VMap[x][y - 1] = var.VMap[x][y];

                }
                if (meth.GetConductive(var.Map[x + 1][y])) {
                    var.VMap[x + 1][y] = var.VMap[x][y];
                }
                if (meth.GetConductive(var.Map[x - 1][y])) {
                    var.VMap[x - 1][y] = var.VMap[x][y];
                }
            } else if (var.Map[x][y] == 6)//Copper
            {

                if (meth.GetConductive(var.Map[x][y + 1])) {
                    if (var.Map[x][y + 1] != 11) {
                        if (var.VMap[x][y + 1] < var.VMap[x][y]) {
                            var.VMap[x][y] -= 1;
                            var.VMap[x][y + 1] = var.VMap[x][y];
                        }
                    } else if (var.VMap[x][y] > 29) {
                        var.VMap[x][y] -= 30;
                        var.VMap[x][y + 1] += 30;
                    }
                }


                if (meth.GetConductive(var.Map[x][y - 1])) {
                    if (var.VMap[x][y - 1] < var.VMap[x][y]) {
                        var.VMap[x][y] -= 1;
                        var.VMap[x][y - 1] = var.VMap[x][y];
                    }
                }


                if (meth.GetConductive(var.Map[x + 1][y])) {
                    if (var.VMap[x + 1][y] < var.VMap[x][y]) {
                        var.VMap[x][y] -= 1;
                        var.VMap[x + 1][y] = var.VMap[x][y];
                    }

                }


                if (meth.GetConductive(var.Map[x - 1][y])) {
                    if (var.VMap[x - 1][y] < var.VMap[x][y]) {
                        var.VMap[x][y] -= 1;
                        var.VMap[x - 1][y] = var.VMap[x][y];
                    }
                }

            } else if (var.Map[x][y] == 7)//Semi Conductor A
            {

                if (var.Map[x][y + 1] == 8 || var.Map[x][y + 1] == 7) {
                    if (var.VMap[x][y + 1] < var.VMap[x][y]) {
                        var.VMap[x][y] -= 1;
                        var.VMap[x][y + 1] = var.VMap[x][y];
                    }
                }
                if (var.Map[x][y - 1] == 8 || var.Map[x][y - 1] == 7) {
                    if (var.VMap[x][y - 1] < var.VMap[x][y]) {
                        var.VMap[x][y] -= 1;
                        var.VMap[x][y - 1] = var.VMap[x][y];
                    }
                }
                if (var.Map[x + 1][y] == 8 || var.Map[x + 1][y] == 7) {
                    if (var.VMap[x + 1][y] < var.VMap[x][y]) {
                        var.VMap[x][y] -= 1;
                        var.VMap[x + 1][y] = var.VMap[x][y];
                    }
                }
                if (var.Map[x - 1][y] == 8 || var.Map[x - 1][y] == 7) {
                    if (var.VMap[x - 1][y] < var.VMap[x][y]) {
                        var.VMap[x][y] -= 1;
                        var.VMap[x - 1][y] = var.VMap[x][y];
                    }
                }
            } else if (var.Map[x][y] == 8)//Semi Conductor B
            {

                if (var.Map[x][y + 1] == 4 || var.Map[x][y + 1] == 6 || var.Map[x][y + 1] == 8) {
                    if (var.VMap[x][y + 1] < var.VMap[x][y]) {
                        var.VMap[x][y] -= 1;
                        var.VMap[x][y + 1] = var.VMap[x][y];
                    }
                }
                if (var.Map[x][y - 1] == 4 || var.Map[x][y - 1] == 6 || var.Map[x][y - 1] == 8) {
                    if (var.VMap[x][y - 1] < var.VMap[x][y]) {
                        var.VMap[x][y] -= 1;
                        var.VMap[x][y - 1] = var.VMap[x][y];
                    }
                }
                if (var.Map[x + 1][y] == 4 || var.Map[x + 1][y] == 6 || var.Map[x + 1][y] == 8) {
                    if (var.VMap[x + 1][y] < var.VMap[x][y]) {
                        var.VMap[x][y] -= 1;
                        var.VMap[x + 1][y] = var.VMap[x][y];
                    }
                }
                if (var.Map[x - 1][y] == 4 || var.Map[x - 1][y] == 6 || var.Map[x - 1][y] == 8) {
                    if (var.VMap[x - 1][y] < var.VMap[x][y]) {
                        var.VMap[x][y] -= 1;
                        var.VMap[x - 1][y] = var.VMap[x][y];
                    }
                }
            } else if (var.Map[x][y] == 9)//Screen
            {
                if (var.VMap[x][y] >= 50) {
                    var.VMap[x][y] -= 50;
                } else {
                    var.VMap[x][y] = 0;
                }

            } else if (var.Map[x][y] == 11)//Rechargable Battery
            {
                if (var.VMap[x][y] > 5000) {
                    var.VMap[x][y] = 5000;
                }
                if (meth.GetConductive(var.Map[x][y + 1])) {
                    if (var.VMap[x][y + 1] < var.VMap[x][y]) {
                        if (var.VMap[x][y] >= var.PMap[x][y] * 10)//If we have more then 50 Volts
                        {
                            if (var.VMap[x][y + 1] < var.PMap[x][y] * 5)//If the Target have less then 50 Volts
                            {
                                var.VMap[x][y] -= var.PMap[x][y] * 10;
                                var.VMap[x][y + 1] = (short) (var.PMap[x][y] * 10);
                            }

                        } else {
                            if (var.VMap[x][y + 1] < var.PMap[x][y] * 10) {
                                var.VMap[x][y + 1] += var.VMap[x][y];
                                var.VMap[x][y] = 0;
                            }
                        }
                    }
                }

            } else if (var.Map[x][y] == 10)//Resistor
            {

                if (meth.GetConductive(var.Map[x][y + 1])) {
                    if (var.VMap[x][y + 1] < var.VMap[x][y]) {
                        if (var.VMap[x][y] >= (var.PMap[x][y] * 10)) {
                            var.VMap[x][y] -= (var.PMap[x][y] * 10);
                            var.VMap[x][y + 1] = (short) (var.PMap[x][y] * 10);
                        }
                    }
                }
                if (meth.GetConductive(var.Map[x][y - 1])) {
                    if (var.VMap[x][y - 1] < var.VMap[x][y]) {
                        if (var.VMap[x][y] >= (var.PMap[x][y] * 10)) {
                            var.VMap[x][y] -= (var.PMap[x][y] * 10);
                            var.VMap[x][y - 1] = (short) (var.PMap[x][y] * 10);
                        }
                    }
                }
                if (meth.GetConductive(var.Map[x + 1][y])) {
                    if (var.VMap[x + 1][y] < var.VMap[x][y]) {
                        if (var.VMap[x][y] >= (var.PMap[x][y] * 10)) {
                            var.VMap[x][y] -= (var.PMap[x][y] * 10);
                            var.VMap[x + 1][y] = (short) (var.PMap[x][y] * 10);
                        }
                    }
                }
                if (meth.GetConductive(var.Map[x - 1][y])) {
                    if (var.VMap[x - 1][y] < var.VMap[x][y]) {
                        if (var.VMap[x][y] >= (var.PMap[x][y] * 10)) {
                            var.VMap[x][y] -= (var.PMap[x][y] * 10);
                            var.VMap[x - 1][y] = (short) (var.PMap[x][y] * 10);
                        }
                    }
                }
            } else if (var.Map[x][y] == 12)//Power Drainer
            {
                var.VMap[x][y] = 0;

                if (var.Map[x][y + 1] == 4 || var.Map[x][y + 1] == 6) {
                    if (var.VMap[x][y + 1] > 0) {
                        var.VMap[x][y + 1] = 0;
                    }
                }
                if (var.Map[x][y - 1] == 4 || var.Map[x][y - 1] == 6) {
                    if (var.VMap[x][y - 1] > 0) {
                        var.VMap[x][y - 1] = 0;
                    }
                }
                if (var.Map[x + 1][y] == 4 || var.Map[x + 1][y] == 6) {
                    if (var.VMap[x + 1][y] > 0) {
                        var.VMap[x + 1][y] = 0;
                    }
                }
                if (var.Map[x - 1][y] == 4 || var.Map[x - 1][y] == 6) {
                    if (var.VMap[x - 1][y] > 0) {

                        var.VMap[x - 1][y] = 0;
                    }
                }
            } else if (var.Map[x][y] == 14)//Switch
            {
                if (var.PMap[x][y] > 25) {
                    if (meth.GetConductive(var.Map[x][y + 1])) {
                        if (var.VMap[x][y + 1] < var.VMap[x][y]) {
                            var.VMap[x][y] -= 1;
                            var.VMap[x][y + 1] = var.VMap[x][y];
                        }
                    }


                    if (meth.GetConductive(var.Map[x][y - 1])) {
                        if (var.VMap[x][y - 1] < var.VMap[x][y]) {
                            var.VMap[x][y] -= 1;
                            var.VMap[x][y - 1] = var.VMap[x][y];
                        }
                    }


                    if (meth.GetConductive(var.Map[x + 1][y])) {
                        if (var.VMap[x + 1][y] < var.VMap[x][y]) {
                            var.VMap[x][y] -= 1;
                            var.VMap[x + 1][y] = var.VMap[x][y];
                        }

                    }


                    if (meth.GetConductive(var.Map[x - 1][y])) {
                        if (var.VMap[x - 1][y] < var.VMap[x][y]) {
                            var.VMap[x][y] -= 1;
                            var.VMap[x - 1][y] = var.VMap[x][y];
                        }
                    }

                }
            }
        }


    }//End of Voltage Update

    @SuppressWarnings("static-access")
    public void UpdatePowder(int x, int y) {
        if (y <= 2 || y >= var.Height - 2 || x >= var.Width - 2 || x <= 2)//If it's out border
        {
            var.Map[x][y] = -127;//Destroy it
            return;
        }

        if (var.Map[x][y + 1] == -127)//And If the space under it is free
        {
            //Make the current space free

            var.RandomNum = rand.nextInt(5);//Get a random Value

            if (var.RandomNum == 1 && var.Map[x + 1][y + 1] == -127) {
                var.Map[x + 1][y + 1] = var.Map[x][y];//Occupy the tile under
                var.BMap[x + 1][y + 1] = true;//Make sure it won't be moved again
                var.Map[x][y] = -127;
                return;
            } else if (var.RandomNum == 2 && var.Map[x - 1][y + 1] == -127) {
                var.Map[x - 1][y + 1] = var.Map[x][y];//Occupy the tile under
                var.BMap[x - 1][y + 1] = true;//Make sure it won't be moved again
                var.Map[x][y] = -127;
                return;
            } else {
                var.Map[x][y + 1] = var.Map[x][y];//Occupy the tile under
                var.BMap[x][y + 1] = true;//Make sure it won't be moved again
                var.Map[x][y] = -127;
                return;
            }


        } else//If the space under is not free
        {
            if (var.Map[x + 1][y + 1] == -127 || var.Map[x - 1][y - 1] == -127)//If either of the one Down-Left or Down-Right are free
            {
                if (rand.nextBoolean())//Random Value Case One:
                {

                    if (var.Map[x + 1][y + 1] == -127)//If Down Right is Clear
                    {

                        var.Map[x + 1][y + 1] = var.Map[x][y];//Move Down Right
                        var.BMap[x + 1][y + 1] = true;//Make sure it won't be moved again
                        var.Map[x][y] = -127;
                        return;
                    } else {
                        if (var.Map[x - 1][y + 1] == -127)//If Down Left is Clear
                        {

                            var.Map[x - 1][y + 1] = var.Map[x][y];//Move Down Left
                            var.BMap[x - 1][y + 1] = true;//Make sure it won't be moved again
                            var.Map[x][y] = -127;
                            return;
                        }
                    }
                } else//Random Value Case Two
                {
                    if (var.Map[x - 1][y + 1] == -127)//If Down Left is Clear
                    {
                        var.Map[x][y] = -127;
                        var.Map[x - 1][y + 1] = 0;//Move Down Left
                        var.BMap[x - 1][y + 1] = true;//Make sure it won't be moved again
                        return;
                    } else {
                        if (var.Map[x + 1][y + 1] == -127)//If Down Right is Clear
                        {
                            var.Map[x][y] = -127;
                            var.Map[x + 1][y + 1] = 0;//Move Down Right
                            var.BMap[x + 1][y + 1] = true;//Make sure it won't be moved again
                            return;
                        }
                    }
                }
            }

            //////////////////////////

            if (meth.getWeight(var.Map[x][y + 1]) < meth.getWeight(var.Map[x][y]))//And If the space under it is free
            {
                //Make the current space free

                var.RandomNum = rand.nextInt(5);//Get a random Value

                if (var.RandomNum == 1 && meth.getWeight(var.Map[x + 1][y + 1]) < meth.getWeight(var.Map[x][y])) {
                    var.element = var.Map[x + 1][y + 1];
                    var.Map[x + 1][y + 1] = var.Map[x][y];//Occupy the tile under
                    var.BMap[x + 1][y + 1] = true;//Make sure it won't be moved again
                    var.Map[x][y] = var.element;
                    return;
                } else if (var.RandomNum == 2 && meth.getWeight(var.Map[x - 1][y + 1]) < meth.getWeight(var.Map[x][y])) {
                    var.element = var.Map[x - 1][y + 1];
                    var.Map[x - 1][y + 1] = var.Map[x][y];//Occupy the tile under
                    var.BMap[x - 1][y + 1] = true;//Make sure it won't be moved again
                    var.Map[x][y] = var.element;
                    return;
                } else {
                    var.element = var.Map[x][y + 1];
                    var.Map[x][y + 1] = var.Map[x][y];//Occupy the tile under
                    var.BMap[x][y + 1] = true;//Make sure it won't be moved again
                    var.Map[x][y] = var.element;
                    return;
                }


            } else//If the space under is not free
            {
                if (meth.getWeight(var.Map[x + 1][y + 1]) < meth.getWeight(var.Map[x][y]) || meth.getWeight(var.Map[x - 1][y - 1]) < meth.getWeight(var.Map[x][y]))//If either of the one Down-Left or Down-Right are free
                {
                    if (rand.nextBoolean())//Random Value Case One:
                    {

                        if (meth.getWeight(var.Map[x + 1][y + 1]) < meth.getWeight(var.Map[x][y]))//If Down Right is Clear
                        {
                            var.element = var.Map[x + 1][y + 1];
                            var.Map[x + 1][y + 1] = var.Map[x][y];//Move Down Right
                            var.BMap[x + 1][y + 1] = true;//Make sure it won't be moved again
                            var.Map[x][y] = var.element;
                            return;
                        } else {
                            if (meth.getWeight(var.Map[x - 1][y + 1]) < meth.getWeight(var.Map[x][y]))//If Down Left is Clear
                            {
                                var.element = var.Map[x - 1][y + 1];
                                var.Map[x - 1][y + 1] = var.Map[x][y];//Move Down Left
                                var.BMap[x - 1][y + 1] = true;//Make sure it won't be moved again
                                var.Map[x][y] = var.element;
                                return;
                            }
                        }
                    } else//Random Value Case Two
                    {
                        if (meth.getWeight(var.Map[x - 1][y + 1]) < meth.getWeight(var.Map[x][y]))//If Down Left is Clear
                        {
                            var.element = var.Map[x - 1][y + 1];
                            var.Map[x - 1][y + 1] = var.Map[x][y];//Move Down Left
                            var.BMap[x - 1][y + 1] = true;//Make sure it won't be moved again
                            var.Map[x][y] = var.element;
                            return;
                        } else {
                            if (meth.getWeight(var.Map[x + 1][y + 1]) < meth.getWeight(var.Map[x][y]))//If Down Right is Clear
                            {
                                var.element = var.Map[x + 1][y + 1];
                                var.Map[x + 1][y + 1] = var.Map[x][y];//Move Down Right
                                var.BMap[x + 1][y + 1] = true;//Make sure it won't be moved again
                                var.Map[x][y] = var.element;
                                return;
                            }
                        }
                    }
                }
            }
        }
    }


    @SuppressWarnings("static-access")
    public void UpdateLiquids(int x, int y) {
        if (y <= 2 || y >= var.Height - 2 || x >= var.Width - 2 || x <= 2)//If it's out border
        {
            var.Map[x][y] = -127;//Destroy it
            return;
        }

        if (var.Map[x][y + 1] == -127)//If the Tile down is free
        {
            var.RandomNum = rand.nextInt(8);//Get a random variable

            if (var.RandomNum == 7 && var.Map[x + 1][y + 1] == -127)// 1/8 Chances that
            {
                var.Map[x + 1][y + 1] = var.Map[x][y];//It moves Right
                var.BMap[x + 1][y + 1] = true;
                var.Map[x][y] = -127;
                return;
            } else if (var.RandomNum == 6 && var.Map[x - 1][y + 1] == -127)// 1/8 Chances that
            {
                var.Map[x - 1][y + 1] = var.Map[x][y];//It moves Left
                var.BMap[x - 1][y + 1] = true;
                var.Map[x][y] = -127;
                return;

            } else// 6/8 Chances that
            {
                var.Map[x][y + 1] = var.Map[x][y];//It moves down
                var.BMap[x][y + 1] = true;
                var.Map[x][y] = -127;
                return;
            }


        } else//If the tile under it is occupied
        {
            for (int u = 0; u < 6; u++) {
                var.RandomNum = rand.nextInt(7);

                if (var.RandomNum <= 2 && var.Map[x + 1][y + 1] == -127)// If it's case 1 and that the Down Right tile is free
                {
                    var.Map[x + 1][y + 1] = var.Map[x][y];//Move it there
                    var.BMap[x + 1][y + 1] = true;
                    var.Map[x][y] = -127;
                    return;

                } else if (var.RandomNum > 2 && var.RandomNum <= 4 && var.Map[x - 1][y + 1] == -127)// If it's case 2 and that the Down Left tile is free
                {
                    var.Map[x - 1][y + 1] = var.Map[x][y];//Move it there
                    var.BMap[x - 1][y + 1] = true;
                    var.Map[x][y] = -127;
                    return;
                } else if (var.RandomNum == 5 && var.Map[x + 1][y] == -127)// If it's case 1 and that the Right tile is free
                {
                    var.Map[x + 1][y] = var.Map[x][y];//Move it there
                    var.BMap[x + 1][y] = true;
                    var.Map[x][y] = -127;
                    return;
                } else if (var.RandomNum == 6 && var.Map[x - 1][y] == -127)// If it's case 2 and that the Left tile is free
                {
                    var.Map[x - 1][y] = var.Map[x][y];//Move it there
                    var.BMap[x - 1][y] = true;
                    var.Map[x][y] = -127;
                    return;
                }
            }
        }
        ////////////////////////////////

        if (meth.getWeight(var.Map[x][y + 1]) < meth.getWeight(var.Map[x][y]))//If the Tile down is free
        {
            var.RandomNum = rand.nextInt(8);//Get a random variable

            if (var.RandomNum == 7 && meth.getWeight(var.Map[x + 1][y + 1]) < meth.getWeight(var.Map[x][y]))// 1/8 Chances that
            {
                var.element = var.Map[x + 1][y + 1];
                var.Map[x + 1][y + 1] = var.Map[x][y];//It moves Right
                var.BMap[x + 1][y + 1] = true;
                var.Map[x][y] = var.element;
                return;
            } else if (var.RandomNum == 6 && meth.getWeight(var.Map[x - 1][y + 1]) < meth.getWeight(var.Map[x][y]))// 1/8 Chances that
            {
                var.element = var.Map[x - 1][y + 1];
                var.Map[x - 1][y + 1] = var.Map[x][y];//It moves Left
                var.BMap[x - 1][y + 1] = true;
                var.Map[x][y] = var.element;
                return;
            } else// 6/8 Chances that
            {
                var.element = var.Map[x][y + 1];
                var.Map[x][y + 1] = var.Map[x][y];//It moves down
                var.BMap[x][y + 1] = true;
                var.Map[x][y] = var.element;
                return;
            }


        } else//If the tile under it is occupied
        {
            for (int u = 0; u < 6; u++) {
                var.RandomNum = rand.nextInt(7);

                if (var.RandomNum <= 2 && meth.getWeight(var.Map[x + 1][y + 1]) < meth.getWeight(var.Map[x][y]))// If it's case 1 and that the Down Right tile is free
                {
                    var.element = var.Map[x + 1][y + 1];
                    var.Map[x + 1][y + 1] = var.Map[x][y];
                    var.BMap[x + 1][y + 1] = true;
                    var.Map[x][y] = var.element;
                    return;

                } else if (var.RandomNum > 2 && var.RandomNum <= 4 && meth.getWeight(var.Map[x - 1][y + 1]) < meth.getWeight(var.Map[x][y]))// If it's case 2 and that the Down Left tile is free
                {
                    var.element = var.Map[x - 1][y + 1];
                    var.Map[x - 1][y + 1] = var.Map[x][y];
                    var.BMap[x - 1][y + 1] = true;
                    var.Map[x][y] = var.element;
                    return;
                }

                if (var.RandomNum == 5 && meth.getWeight(var.Map[x + 1][y]) < meth.getWeight(var.Map[x][y]))// If it's case 1 and that the Right tile is free
                {
                    var.element = var.Map[x + 1][y];
                    var.Map[x + 1][y] = var.Map[x][y];
                    var.BMap[x + 1][y] = true;
                    var.Map[x][y] = var.element;
                    return;
                } else if (var.RandomNum == 6 && meth.getWeight(var.Map[x - 1][y]) < meth.getWeight(var.Map[x][y]))// If it's case 2 and that the Left tile is free
                {
                    var.element = var.Map[x - 1][y];
                    var.Map[x - 1][y] = var.Map[x][y];
                    var.BMap[x - 1][y] = true;
                    var.Map[x][y] = var.element;
                    return;
                }
            }
        }
    }


    @SuppressWarnings("static-access")
    public void UpdateGasses(int x, int y) {
        if (y <= 2 || y >= var.Height - 2 || x >= var.Width - 2 || x <= 2)//If it's out border
        {
            var.Map[x][y] = -127;//Destroy it
            return;
        }

        if (meth.getWeight(var.Map[x][y + 1]) < meth.getWeight(var.Map[x][y]) ||
                meth.getWeight(var.Map[x][y - 1]) < meth.getWeight(var.Map[x][y]) ||
                meth.getWeight(var.Map[x + 1][y]) < meth.getWeight(var.Map[x][y]) ||
                meth.getWeight(var.Map[x - 1][y]) < meth.getWeight(var.Map[x][y]))//If the space Up, Down, Left or Right is Free
        {
            for (byte o = 0; o < 2; o++)//Safeguard, it only tries 5 times
            {
                var.RandomNum = rand.nextInt(4);//Get a random value

                if (var.RandomNum == 0)//Case one
                {
                    if (y < var.Height) {
                        if (meth.getWeight(var.Map[x][y + 1]) < meth.getWeight(var.Map[x][y]))//If the tile Up is Free
                        {
                            //Make the current tile Free
                            var.Map[x][y + 1] = var.Map[x][y];
                            var.Map[x][y] = -127;//The the tile up Occupied
                            return;//End the loop
                        }
                    }
                }


                if (var.RandomNum == 1)//Case 2
                {
                    if (x < var.Width - 1) {
                        if (meth.getWeight(var.Map[x + 1][y]) < meth.getWeight(var.Map[x][y]))//See Case 1
                        {

                            var.Map[x + 1][y] = var.Map[x][y];
                            var.Map[x][y] = -127;
                            return;
                        }
                    }
                }


                if (var.RandomNum == 2) {
                    if (y > 0) {
                        if (meth.getWeight(var.Map[x][y - 1]) < meth.getWeight(var.Map[x][y]))//See Case 1
                        {

                            var.Map[x][y - 1] = var.Map[x][y];
                            var.Map[x][y] = -127;
                            return;
                        }
                    }
                }


                if (var.RandomNum == 3) {
                    if (x > 0) {
                        if (meth.getWeight(var.Map[x - 1][y]) < meth.getWeight(var.Map[x][y]))//See Case 1
                        {

                            var.Map[x - 1][y] = var.Map[x][y];
                            var.Map[x][y] = -127;
                            return;
                        }
                    }
                }
            }
        }//End of 'For' Loop


        ////////////////////////

        for (int a = 0; a < 2; a++) {
            if (var.Map[x][y + 1] == -127 ||
                    var.Map[x][y - 1] == -127 ||
                    var.Map[x + 1][y] == -127 ||
                    var.Map[x - 1][y] == -127)//If the space Up, Down, Left or Right is Free
            {
                for (byte o = 0; o < 2; o++)//Safeguard, it only tries 5 times
                {
                    var.RandomNum = rand.nextInt(4);//Get a random value
                }
                if (var.RandomNum == 0)//Case one
                {
                    if (y < var.Height) {
                        if (var.Map[x][y + 1] == -127)//If the tile Up is Free
                        {
                            //Make the current tile Free
                            var.Map[x][y + 1] = var.Map[x][y];
                            var.Map[x][y] = -127;//The the tile up Occupied
                            return;//End the loop
                        }
                    }
                }


                if (var.RandomNum == 1)//Case 2
                {
                    if (x < var.Width - 1) {
                        if (var.Map[x + 1][y] == -127)//See Case 1
                        {

                            var.Map[x + 1][y] = var.Map[x][y];
                            var.Map[x][y] = -127;
                            return;
                        }
                    }
                }


                if (var.RandomNum == 2) {
                    if (y > 0) {
                        if (var.Map[x][y - 1] == -127)//See Case 1
                        {

                            var.Map[x][y - 1] = var.Map[x][y];
                            var.Map[x][y] = -127;
                            return;
                        }
                    }
                }


                if (var.RandomNum == 3) {
                    if (x > 0) {
                        if (var.Map[x - 1][y] == -127)//See Case 1
                        {

                            var.Map[x - 1][y] = var.Map[x][y];
                            var.Map[x][y] = -127;
                            return;
                        }
                    }
                }
            }
        }
    }

}
