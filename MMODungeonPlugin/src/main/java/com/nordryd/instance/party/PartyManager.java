package com.nordryd.instance.party;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

/**
 * <p>
 * Class for when you wanna PARTAAAAAAY!!! <i>WOOOOOOOOOOOO!!!!</i>
 * </p>
 * 
 * @author Nordryd
 */
public class PartyManager
{
    private static final List<Party> PARTIES = new ArrayList<>();
    
    // maybe use a map to associate parties with instances
    
    public static Party getPartyFromId(long id) {
        for(Party party : PARTIES) {
            if(party.getPartyID() == id) {
                return party;
            }
        }
        
        throw new IllegalArgumentException("Invalid party ID given. Party either does not exist or has been disbanded");
    }
    
    public static void newParty(Player... players) {
        
    }
    
    public static void removeParty() {
        
    }
    
    public static void broadcastToParty(long id, String message) {
        for(Player player : getPartyFromId(id).getPartyMembers()) {
            player.sendMessage(message);
        }
    }
    
    public static void getPlayerPartyID(Player player) {
        
    }
}
