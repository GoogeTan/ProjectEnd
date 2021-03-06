package phoenix.network

import net.minecraft.client.entity.player.ClientPlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.network.PacketBuffer
import phoenix.utils.Date
import phoenix.utils.IChapterReader
import phoenix.utils.readDate
import phoenix.utils.writeDate
import phoenix.utils.Pair

class SyncBookPacket(var list : List<Pair<Int, Date>>): NetworkHandler.Packet()
{
    override fun encode(packet: NetworkHandler.Packet, buf: PacketBuffer)
    {
        if(packet is SyncBookPacket)
        {
            list = packet.list
            buf.writeInt(list.size)
            for (i in list)
            {
                buf.writeInt(i.key)
                buf.writeDate(i.value)
            }
        }
    }

    override fun decode(buf: PacketBuffer): NetworkHandler.Packet
    {
        val res = ArrayList<Pair<Int, Date>>()

        val count = buf.readInt()
        for (i in 0 until count)
        {
            val id = buf.readInt()
            val date = buf.readDate()
            res.add(Pair(id, date))
        }

        return SyncBookPacket(res)
    }

    override fun client(player: ClientPlayerEntity?)
    {
        if(player is IChapterReader)
        {
            for (i in list)
                player.addChapter(i.key, i.value)
        }
    }

    override fun server(player: ServerPlayerEntity?) {}
}