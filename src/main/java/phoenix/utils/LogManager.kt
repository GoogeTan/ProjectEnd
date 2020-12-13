package phoenix.utils

import org.apache.logging.log4j.Level
import phoenix.Phoenix
import phoenix.init.PhoenixConfiguration
import java.lang.Exception

object LogManager
{
    @JvmStatic
    fun log(obj : Any, message : String) : Unit
    {
        if(PhoenixConfiguration.COMMON_CONFIG.debug.get())
        {
            Phoenix.LOGGER.error("${obj.javaClass} " + message)
        }
        else
        {
            Phoenix.LOGGER.log(Level.DEBUG, "${obj.javaClass} " + message)
        }
    }

    @JvmStatic
    fun error(obj : Any, message : String?)
    {
        Phoenix.LOGGER.error("${obj.javaClass} " + (message ?: ""))
    }

    @JvmStatic
    fun error(obj : Any, message : Exception?)
    {
        if(message != null)
            Phoenix.LOGGER.error("Exception in class ${obj.javaClass.name}: " + message.toString())
    }
}