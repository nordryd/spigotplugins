package com.nordryd.instance.party;

import java.util.List;

import org.bukkit.entity.Player;

public interface Party
{
    public long getPartyID();
    
    public List<Player> getPartyMembers();
    
    public void addPlayer();
    
    public void removePlayer();
    
    public Player getLeader();
    
    public void changeLeader();
}
