package org.example.gameoflifegui;

import java.util.BitSet;

public class ManguOlek {
    public static BitSet järgmineOlek(BitSet eelmine, int lauaLaius, int lauaPikkus) {
        BitSet järgmine = new BitSet(lauaLaius * lauaPikkus);
        for (int y = 0; y < lauaPikkus; y++) {
            for (int x = 0; x < lauaLaius; x++) {
                int elusaidNaabreid = 0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        if (dx == 0 && dy == 0) continue;
                        int nx = x + dx;
                        int ny = y + dy;
                        if (nx < 0 || nx >= lauaLaius || ny < 0 || ny >= lauaPikkus) continue;
                        if (eelmine.get(ny * lauaLaius + nx)) elusaidNaabreid++;
                    }
                }
                if (eelmine.get(y * lauaLaius + x)) {
                    if (elusaidNaabreid == 2 || elusaidNaabreid == 3) järgmine.set(y * lauaLaius + x);
                } else {
                    if (elusaidNaabreid == 3) järgmine.set(y * lauaLaius + x);
                }
            }
        }
        return järgmine;
    }
}
