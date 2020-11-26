function initializeCoreMod() {
    return {
        'potion': {
            'target': {
                'type': 'CLASS',
                'name': 'net.minecraft.potion.EffectInstance'
            },
            'transformer': function(classNode) {
                var asmapi=Java.type('net.minecraftforge.coremod.api.ASMAPI')
                var fn = asmapi.mapField('field_202296_ap') // potion field - remap to mcp if necessary
                asmapi.redirectFieldToMethod(classNode, fn, asmapi.mapMethod('func_188419_a'))
                return classNode;
            }
        }
    }
}