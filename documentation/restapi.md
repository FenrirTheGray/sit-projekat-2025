# REST API
This document describes how to access and use data acquired from the restapi.
> By default the api will be running on port 7999.

When the api is started you can check out the OpenAPI documentation at http://localhost:7999/swagger-ui/index.html

## Article
<table>
  <tr>
    <th>Method</th>
    <th>Endpoint</th>
    <th>Body</th>
    <th>Response</th>
    <th>Note</th>
  </tr>
  <tr>
    <td valign="top"><b>GET</b></td>
    <td valign="top">http://localhost:7999/api/articles</td>
    <td valign="top"></td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
[{
    "key": "202",
    "name": "Article a1",
    "description": "Description for a1 article.",
    "imageUrl": "https://cdn.dribbble.com/userupload/22570626/file/original-379b4978ee41eeb352e0ddacbaa6df96.jpg?resize=512x384",
    "basePrice": 135.0,
    "active": true,
    "category": {
        "id": "sldc37b1d1-8f8f-43e6-b5ea-2e6ab870425b",
        "name": "drink",
        "description": "Drinks",
        "active": true
    },
    "modifiers": [
        {
            "key": "187",
            "name": "Modifier m11",
            "description": "Description for Modifier m11",
            "price": 110.0,
            "active": true,
            "type": {
                "id": "sl6840845a-4356-4f04-974c-2d4caaae840d",
                "name": "topping",
                "active": true
            }
        },
        {
            "key": "188",
            "name": "Modifier m12",
            "description": "Description for Modifier m12",
            "price": 120.0,
            "active": true,
            "type": {
                "id": "sl6840845a-4356-4f04-974c-2d4caaae840d",
                "name": "topping",
                "active": true
            }
        }
    ]
},
{
    "key": "203",
    "name": "Article a2",
    "description": "Description for a2 article.",
    "imageUrl": "https://cdn.dribbble.com/userupload/22570626/file/original-379b4978ee41eeb352e0ddacbaa6df96.jpg?resize=512x384",
    "basePrice": 308.0,
    "active": true,
    "category": {
        "id": "sl0edd27ff-6c68-4f76-ace3-83370ee79ca0",
        "name": "burger",
        "description": "Burgers",
        "active": true
    },
    "modifiers": [
        {
            "key": "183",
            "name": "Modifier m7",
            "description": "Description for Modifier m7",
            "price": 70.0,
            "active": true,
            "type": {
                "id": "sl5160267b-1a13-4cdc-bad6-ae3000c726de",
                "name": "size",
                "active": true
            }
        },
        {
            "key": "184",
            "name": "Modifier m8",
            "description": "Description for Modifier m8",
            "price": 80.0,
            "active": true,
            "type": {
                "id": "sl6840845a-4356-4f04-974c-2d4caaae840d",
                "name": "topping",
                "active": true
            }
        },...
]
            </pre>
        </details>
    </td>
    <td valign="top"></td>
  </tr>
  <tr>
    <td valign="top"><b>GET</b></td>
    <td valign="top">http://localhost:7999/api/articles/<code>{key}</code></td>
    <td valign="top"></td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "key": "204",
    "name": "Article a3",
    "description": "Description for a3 article.",
    "imageUrl": "https://cdn.dribbble.com/userupload/22570626/file/original-379b4978ee41eeb352e0ddacbaa6df96.jpg?resize=512x384",
    "basePrice": 615.0,
    "active": true,
    "category": {
        "id": "sl0edd27ff-6c68-4f76-ace3-83370ee79ca0",
        "name": "burger",
        "description": "Burgers",
        "active": true
    },
    "modifiers": [
        {
            "key": "186",
            "name": "Modifier m10",
            "description": "Description for Modifier m10",
            "price": 100.0,
            "active": true,
            "type": {
                "id": "sl5160267b-1a13-4cdc-bad6-ae3000c726de",
                "name": "size",
                "active": true
            }
        },
        {
            "key": "187",
            "name": "Modifier m11",
            "description": "Description for Modifier m11",
            "price": 110.0,
            "active": true,
            "type": {
                "id": "sl6840845a-4356-4f04-974c-2d4caaae840d",
                "name": "topping",
                "active": true
            }
        }
    ]
}
            </pre>
        </details>
    </td>
    <td valign="top"></td>
  </tr>
  <tr>
    <td valign="top"><b>POST</b></td>
    <td valign="top">http://localhost:7999/api/articles</td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "name": "Article a2",
    "description": "Description for a2 article.",
    "imageUrl": "https://cdn.dribbble.com/userupload/22570626/file/original-379b4978ee41eeb352e0ddacbaa6df96.jpg?resize=512x384",
    "basePrice": 308.0,
    "active": true,
    "categoryId":  "sl0edd27ff-6c68-4f76-ace3-83370ee79ca0",
    "modifiers": ["184", "183"]
}
            </pre>
        </details>
    </td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "key": "805",
    "name": "Article a2",
    "description": "Description for a2 article.",
    "imageUrl": "https://cdn.dribbble.com/userupload/22570626/file/original-379b4978ee41eeb352e0ddacbaa6df96.jpg?resize=512x384",
    "basePrice": 308.0,
    "active": true,
    "category": {
        "id": "sl0edd27ff-6c68-4f76-ace3-83370ee79ca0",
        "name": "burger",
        "description": "Burgers",
        "active": true
    },
    "modifiers": [
        {
            "key": "184",
            "name": "Modifier m8",
            "description": "Description for Modifier m8",
            "price": 80.0,
            "active": true,
            "type": {
                "id": "sl6840845a-4356-4f04-974c-2d4caaae840d",
                "name": "topping",
                "active": true
            }
        },
        {
            "key": "183",
            "name": "Modifier m7",
            "description": "Description for Modifier m7",
            "price": 70.0,
            "active": true,
            "type": {
                "id": "sl5160267b-1a13-4cdc-bad6-ae3000c726de",
                "name": "size",
                "active": true
            }
        }
    ]
}
            </pre>
        </details>
    </td>
    <td valign="top"></td>
  </tr>
  <tr>
    <td valign="top"><b>PUT</b></td>
    <td valign="top">http://localhost:7999/api/articles/<code>{key}</code></td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "key": "805",
    "name": "Article a2",
    "description": "Description for a2 article.",
    "imageUrl": "https://cdn.dribbble.com/userupload/22570626/file/original-379b4978ee41eeb352e0ddacbaa6df96.jpg?resize=512x384",
    "basePrice": 308.0,
    "active": true,
    "categoryId":  "sl0edd27ff-6c68-4f76-ace3-83370ee79ca0",
    "modifiers": ["184", "183"]
}
            </pre>
        </details>
    </td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "key": "805",
    "name": "Article a2",
    "description": "Description for a2 article.",
    "imageUrl": "https://cdn.dribbble.com/userupload/22570626/file/original-379b4978ee41eeb352e0ddacbaa6df96.jpg?resize=512x384",
    "basePrice": 308.0,
    "active": true,
    "category": {
        "id": "sl0edd27ff-6c68-4f76-ace3-83370ee79ca0",
        "name": "burger",
        "description": "Burgers",
        "active": true
    },
    "modifiers": [
        {
            "key": "184",
            "name": "Modifier m8",
            "description": "Description for Modifier m8",
            "price": 80.0,
            "active": true,
            "type": {
                "id": "sl6840845a-4356-4f04-974c-2d4caaae840d",
                "name": "topping",
                "active": true
            }
        },
        {
            "key": "183",
            "name": "Modifier m7",
            "description": "Description for Modifier m7",
            "price": 70.0,
            "active": true,
            "type": {
                "id": "sl5160267b-1a13-4cdc-bad6-ae3000c726de",
                "name": "size",
                "active": true
            }
        }
    ]
}
            </pre>
        </details>
    </td>
    <td>key in endpoint and in body must match</td>
  </tr>
  <tr>
    <td valign="top"><b>DELETE</b></td>
    <td valign="top">http://localhost:7999/api/articles/<code>{key}</code></td>
    <td valign="top"></td>
    <td valign="top"></td>
    <td valign="top"></td>
  </tr>
</table>

## Modifier
<table>
  <tr>
    <th>Method</th>
    <th>Endpoint</th>
    <th>Body</th>
    <th>Response</th>
    <th>Note</th>
  </tr>
  <tr>
    <td valign="top"><b>GET</b></td>
    <td valign="top">http://localhost:7999/api/modifiers</td>
    <td valign="top"></td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
[
    {
        "key": "177",
        "name": "Modifier m1",
        "description": "Description for Modifier m1",
        "price": 10.0,
        "active": true,
        "type": {
            "id": "sl6840845a-4356-4f04-974c-2d4caaae840d",
            "name": "topping",
            "active": true
        }
    },
    {
        "key": "178",
        "name": "Modifier m2",
        "description": "Description for Modifier m2",
        "price": 20.0,
        "active": true,
        "type": {
            "id": "sl6840845a-4356-4f04-974c-2d4caaae840d",
            "name": "topping",
            "active": true
        }
    },...
]
            </pre>
        </details>
    </td>
    <td valign="top"></td>
  </tr>
  <tr>
    <td valign="top"><b>GET</b></td>
    <td valign="top">http://localhost:7999/api/modifiers/<code>{key}</code></td>
    <td valign="top"></td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "key": "178",
    "name": "Modifier m2",
    "description": "Description for Modifier m2",
    "price": 20.0,
    "active": true,
    "type": {
        "id": "sl6840845a-4356-4f04-974c-2d4caaae840d",
        "name": "topping",
        "active": true
    }
}
            </pre>
        </details>
    </td>
    <td valign="top"></td>
  </tr>
  <tr>
    <td valign="top"><b>POST</b></td>
    <td valign="top">http://localhost:7999/api/modifiers</td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "name": "Modifier m2",
    "description": "Description for Modifier m2",
    "price": 20.0,
    "active": true,
    "typeId": "sl6840845a-4356-4f04-974c-2d4caaae840d"
}
            </pre>
        </details>
    </td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "key": "1144",
    "name": "Modifier m2",
    "description": "Description for Modifier m2",
    "price": 20.0,
    "active": true,
    "type": {
        "id": "sl6840845a-4356-4f04-974c-2d4caaae840d",
        "name": "topping",
        "active": true
    }
}
            </pre>
        </details>
    </td>
    <td valign="top"></td>
  </tr>
  <tr>
    <td valign="top"><b>PUT</b></td>
    <td valign="top">http://localhost:7999/api/modifiers/<code>{key}</code></td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "key": "1144",
    "name": "Modifier m2",
    "description": "Description for Modifier m2",
    "price": 20.0,
    "active": true,
    "typeId": "sl6840845a-4356-4f04-974c-2d4caaae840d"
}
            </pre>
        </details>
    </td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "key": "1144",
    "name": "Modifier m2",
    "description": "Description for Modifier m2",
    "price": 20.0,
    "active": true,
    "type": {
        "id": "sl6840845a-4356-4f04-974c-2d4caaae840d",
        "name": "topping",
        "active": true
    }
}
            </pre>
        </details>
    </td>
    <td>key in endpoint and in body must match</td>
  </tr>
  <tr>
    <td valign="top"><b>DELETE</b></td>
    <td valign="top">http://localhost:7999/api/modifiers/<code>{key}</code></td>
    <td valign="top"></td>
    <td valign="top"></td>
    <td valign="top"></td>
  </tr>
</table>

## ModifierType
<table>
  <tr>
    <th>Method</th>
    <th>Endpoint</th>
    <th>Body</th>
    <th>Response</th>
    <th>Note</th>
  </tr>
  <tr>
    <td valign="top"><b>GET</b></td>
    <td valign="top">http://localhost:7999/api/modifiertypes</td>
    <td valign="top"></td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
[
    {
        "id": "sl5160267b-1a13-4cdc-bad6-ae3000c726de",
        "name": "size",
        "active": true
    },
    {
        "id": "sl6840845a-4356-4f04-974c-2d4caaae840d",
        "name": "topping",
        "active": true
    },
    {
        "id": "sl5bc6c8e6-6146-4f22-86d2-0d225ed7ca7c",
        "name": "salad",
        "active": true
    }
]
            </pre>
        </details>
    </td>
    <td valign="top"></td>
  </tr>
  <tr>
    <td valign="top"><b>GET</b></td>
    <td valign="top">http://localhost:7999/api/modifiertypes/<code>{id}</code></td>
    <td valign="top"></td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "id": "sl5160267b-1a13-4cdc-bad6-ae3000c726de",
    "name": "size",
    "active": true
}
            </pre>
        </details>
    </td>
    <td valign="top"></td>
  </tr>
  <tr>
    <td valign="top"><b>POST</b></td>
    <td valign="top">http://localhost:7999/api/modifiertypes</td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "name": "size",
    "active": true
}
            </pre>
        </details>
    </td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "id": "slb7b0ad75-419b-4beb-b631-5d873b289dda",
    "name": "size",
    "active": true
}
            </pre>
        </details>
    </td>
    <td valign="top"></td>
  </tr>
  <tr>
    <td valign="top"><b>PUT</b></td>
    <td valign="top">http://localhost:7999/api/modifiertypes/<code>{id}</code></td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "id": "slb7b0ad75-419b-4beb-b631-5d873b289dda",
    "name": "size",
    "active": false
}
            </pre>
        </details>
    </td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "id": "slb7b0ad75-419b-4beb-b631-5d873b289dda",
    "name": "size",
    "active": false
}
            </pre>
        </details>
    </td>
    <td>id in endpoint and in body must match</td>
  </tr>
  <tr>
    <td valign="top"><b>DELETE</b></td>
    <td valign="top">http://localhost:7999/api/modifiertypes/<code>{id}</code></td>
    <td valign="top"></td>
    <td valign="top"></td>
    <td valign="top"></td>
  </tr>
</table>

## Category
<table>
  <tr>
    <th>Method</th>
    <th>Endpoint</th>
    <th>Body</th>
    <th>Response</th>
    <th>Note</th>
  </tr>
  <tr>
    <td valign="top"><b>GET</b></td>
    <td valign="top">http://localhost:7999/api/categories</td>
    <td valign="top"></td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
[
    {
        "id": "sldc37b1d1-8f8f-43e6-b5ea-2e6ab870425b",
        "name": "drink",
        "description": "Drinks",
        "active": true
    },
    {
        "id": "sl0edd27ff-6c68-4f76-ace3-83370ee79ca0",
        "name": "burger",
        "description": "Burgers",
        "active": true
    },
    {
        "id": "sl80d185a9-34ad-411a-bb72-3e9c03126167",
        "name": "pizza",
        "description": "Pizzas",
        "active": true
    }
]
            </pre>
        </details>
    </td>
    <td valign="top"></td>
  </tr>
  <tr>
    <td valign="top"><b>GET</b></td>
    <td valign="top">http://localhost:7999/api/categories/<code>{id}</code></td>
    <td valign="top"></td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "id": "sl0edd27ff-6c68-4f76-ace3-83370ee79ca0",
    "name": "burger",
    "description": "Burgers",
    "active": true
}
            </pre>
        </details>
    </td>
    <td valign="top"></td>
  </tr>
  <tr>
    <td valign="top"><b>POST</b></td>
    <td valign="top">http://localhost:7999/api/categories</td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "name": "grill",
    "description": "Grilled",
    "active": true
}
            </pre>
        </details>
    </td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "id": "sld23599fe-9d86-48dd-9cc4-33410c41d05c",
    "name": "grill",
    "description": "Grilled",
    "active": true
}
            </pre>
        </details>
    </td>
    <td valign="top"></td>
  </tr>
  <tr>
    <td valign="top"><b>PUT</b></td>
    <td valign="top">http://localhost:7999/api/categories/<code>{id}</code></td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "id": "sld23599fe-9d86-48dd-9cc4-33410c41d05c",
    "name": "grill",
    "description": "Grilled stuff",
    "active": true
}
            </pre>
        </details>
    </td>
    <td valign="top">
        <details>
            <summary>Click to see example</summary>
            <pre>
{
    "id": "sld23599fe-9d86-48dd-9cc4-33410c41d05c",
    "name": "grill",
    "description": "Grilled stuff",
    "active": true
}
            </pre>
        </details>
    </td>
    <td>id in endpoint and in body must match</td>
  </tr>
  <tr>
    <td valign="top"><b>DELETE</b></td>
    <td valign="top">http://localhost:7999/api/categories/<code>{id}</code></td>
    <td valign="top"></td>
    <td valign="top"></td>
    <td valign="top"></td>
  </tr>
</table>