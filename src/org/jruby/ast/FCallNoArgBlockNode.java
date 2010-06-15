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
public class FCallNoArgBlockNode extends FCallNode {
    private static final long serialVersionUID = 0L;
    public FCallNoArgBlockNode() {
        super();
    }
    
    // For 'foo'
    public FCallNoArgBlockNode(ISourcePosition position, String name, IterNode iter) {
        super(position, name, null, iter);
    }
    
    // For 'foo()'.  Args are only significant in maintaining backwards compatible AST structure
    public FCallNoArgBlockNode(ISourcePosition position, String name, Node args, IterNode iter) {
        super(position, name, args, iter);
    }

    @Override
    public IRubyObject interpret(Ruby runtime, ThreadContext context, IRubyObject self, Block aBlock) {
        return callAdapter.callIter(context, self, self, RuntimeHelpers.getBlock(context, self, iterNode));
    }
}
