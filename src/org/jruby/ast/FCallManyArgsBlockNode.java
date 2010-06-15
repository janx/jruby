/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jruby.ast;

import org.jruby.Ruby;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

/**
 *
 * @author enebo
 */
public class FCallManyArgsBlockNode extends FCallNode {
    private static final long serialVersionUID = 0L;
    public FCallManyArgsBlockNode() {
        super();
    }
    
    public FCallManyArgsBlockNode(ISourcePosition position, String name, Node args, IterNode iter) {
        super(position, name, args, iter);
    }
    
    @Override
    public IRubyObject interpret(Ruby runtime, ThreadContext context, IRubyObject self, Block aBlock) {
        IRubyObject[] args = ((ArrayNode) getArgsNode()).interpretPrimitive(runtime, context, self, aBlock);
        Block block = RuntimeHelpers.getBlock(context, self, iterNode);

        return callAdapter.call(context, self, self, args, block);
    }
}
