/*
 * jQuery Highlight Regex Plugin v0.1.2
 *
 * Based on highlight v3 by Johann Burkard
 * http://johannburkard.de/blog/programming/javascript/highlight-javascript-text-higlighting-jquery-plugin.html
 *
 * (c) 2009-13 Jacob Rothstein
 * MIT license
 */

;(function( $ ) {



  var normalize = function( node ) {
    if ( ! ( node && node.childNodes )) return

    var children     = $.makeArray( node.childNodes )
    ,   prevTextNode = null

    $.each( children, function( i, child ) {
      if ( child.nodeType === 3 ) {
        if ( child.nodeValue === "" ) {

          node.removeChild( child )

        } else if ( prevTextNode !== null ) {

          prevTextNode.nodeValue += child.nodeValue;
          node.removeChild( child )

        } else {

          prevTextNode = child

        }
      } else {
        prevTextNode = null

        if ( child.childNodes ) {
          normalize( child )
        }
      }
    })
  }




  $.fn.highlightRegex = function( regex, options ) {

    if ( typeof regex === 'object' && !(regex.constructor.name == 'RegExp' || regex instanceof RegExp ) ) {
      options = regex
      regex = undefined
    }

    if ( typeof options === 'undefined' ) options = {}

    options.className = options.className || 'highlight'
    options.tagType   = options.tagType   || 'span'
    options.attrs     = options.attrs     || {}

    if ( typeof regex === 'undefined' || regex.source === '' ) {

      $( this ).find( options.tagType + '.' + options.className ).each( function() {

        $( this ).replaceWith( $( this ).text() )

        normalize( $( this ).parent().get( 0 ))

      })

    } else {

      $( this ).each( function() {

        var elt = $( this ).get( 0 )

        normalize( elt )

        $.each( $.makeArray( elt.childNodes ), function( i, searchnode ) {

          var spannode, middlebit, middleclone, pos, match, parent

          normalize( searchnode )

          if ( searchnode.nodeType == 3 ) {
            
            // don't re-highlight the same node over and over
            if ( $(searchnode).parent(options.tagType + '.' + options.className).length ) {
                return;
            }

            while ( searchnode.data &&
                    ( pos = searchnode.data.search( regex )) >= 0 ) {

              match = searchnode.data.slice( pos ).match( regex )[ 0 ]

              if ( match.length > 0 ) {
                console.log(searchnode);
                console.log(searchnode.data);
                console.log(typeof searchnode);
                spannode = document.createElement( options.tagType )
                spannode.className = options.className
                $(spannode).attr(options.attrs)

                parent      = searchnode.parentNode
                console.log(parent);

                middlebit   = searchnode.splitText( pos )
                console.log(middlebit)
                searchnode  = middlebit.splitText( match.length )
                middleclone = middlebit.cloneNode( true )
                console.log(searchnode)
                spannode.appendChild( middleclone )
                console.log(spannode)
                parent.replaceChild( spannode, middlebit )
                console.log(parent)
              } else break
            }

          } else {

            $( searchnode ).highlightRegex( regex, options )

          }
        })
      })
    }

    return $( this )
  }

  $.fn.highlightRange = function(id, start, end, index) {

    options = {};
    options.className = 'text-highlight';
    options.tagType   = 'span';
    options.attrs     = {'index': index, 'data-trigger': 'focus', 'data-toggle': 'popover',
      'tabindex': '0'};

    var searchnode, spannode, middlebit, middleclone, parent;
    spannode = document.createElement( options.tagType );
    spannode.className = options.className;
    $(spannode).attr(options.attrs);

    parent      = $(id).get(0);
    searchnode = parent.childNodes[0];

    // console.log("i: " + index);
    // console.log("text: " + $(searchnode).text());
    middlebit   = searchnode.splitText(start);
    searchnode  = middlebit.splitText(end - start);
    middleclone = middlebit.cloneNode( true );

    spannode.appendChild( middleclone );
    parent.replaceChild( spannode, middlebit );

    // console.log(parent);
    // console.log($(parent).text());
    return $( this )
  }
})( jQuery );
