
the structure of the application will look like the following:
          +-----------------------+
          |      IPAddress        |
          |-----------------------|
          | -IPv4Address: string  |
          | -IpOctet : int[]      |
          | -prefix:  int		  |
          | -subnetMaskStr: String|
          | -subnetMask : int[]   |
          +-----------------------+
                     ^
                     |
                     |
          +-----------------------+
          |   networkIPAddress    |
          |-----------------------|
          | -ipAddress: IPAddress |
          +-----------------------+
                      ^
                      |
                      |
    +------------------------------------+
    |        SubnettingStrategy           |
    |------------------------------------ |
    | +Subnet(): void (abstract method)   |
    +------------------------------------+
                      ^
                      |
                      |
+-------------------------------------------------------------------------+
|            |                         |                    |             |
|            |                         |                    |             |
|            v                         v                    v             |
| +------------------------+ +----------------+ +------------------------+|
| |FixedNetworksSubnetting | |FixedHostSubnet | |VLSMSubnettingStrategy  ||
| |Strategy                | |tingStrategy    | |                        ||
| +------------------------+ +----------------+ +------------------------+|
| | +Subnet(): void        | | +Subnet(): void| | +Subnet(): void        ||
| +------------------------+ +----------------+ +------------------------+|

