import React from 'react';
import { useRouter } from 'next/router';
import {
  UploadOutlined,
  UserOutlined,
  VideoCameraOutlined,
} from '@ant-design/icons';
import type { MenuProps } from 'antd';
import { Layout, Menu, theme } from 'antd';
import Link from 'next/link';
import { Input, Space } from 'antd';

const { Search } = Input;

const { Header, Content, Footer, Sider } = Layout;

const Success: React.FC = () => {
  const router = useRouter();

  // const token = localStorage?.getItem('token');
  // if (!token) {
  // router.push('/login');

  // const {
  //   token: { colorBgContainer },
  // } = theme.useToken();

  type MenuItem = Required<MenuProps>['items'][number];

  function getItem(
    label: React.ReactNode,
    key: React.Key,
    children?: MenuItem[]
  ): MenuItem {
    return {
      key,
      children,
      label,
    } as MenuItem;
  }

  const items: MenuItem[] = [
    getItem('Dashboard', '1'),
    getItem('Inventory', '2'),
    getItem('Customers', '3'),
    getItem('Orders', '4'),
    getItem('Categories', '5'),
    getItem('Reports', '6'),
    getItem('Sales', '7'),
  ];

  const otherItems: MenuItem[] = [
    getItem('Help', '8'),
    getItem('Settings', '9'),
  ];

  const onSearch = (value: string) => console.log(value);

  return (
    <Layout style={{ height: '100vh' }}>
      <Sider
        breakpoint='lg'
        collapsedWidth='0'
        onBreakpoint={(broken) => {
          console.log(broken);
        }}
        onCollapse={(collapsed, type) => {
          console.log(collapsed, type);
        }}
      >
        <div
          style={{
            height: 32,
            margin: 16,
            background: 'rgba(255, 255, 255, 0.2)',
            display: 'flex',
            justifyContent: 'center',
            color: '#fff',
            alignItems: 'center',
          }}
        >
          Inventory Management
        </div>

        <div className='logo' />
        <div>
          <div>
            <Menu
              theme='dark'
              mode='inline'
              defaultSelectedKeys={['4']}
              items={items}
            />
          </div>

          <div style={{ marginTop: '400px' }}>
            <Menu
              theme='dark'
              mode='inline'
              defaultSelectedKeys={['4']}
              items={otherItems}
            />
          </div>
        </div>
      </Sider>
      <Layout>
        <Header style={{ padding: 0, background: '#fff' }}>
          <Search
            placeholder='input search text'
            onSearch={onSearch}
            style={{ width: 200 }}
          />
        </Header>
        <Content style={{ margin: '24px 16px 0' }}>
          <div
            style={{
              padding: 24,
              minHeight: 360,
              background: '#fff',
            }}
          >
            Dashboard
          </div>
        </Content>
        <Footer style={{ textAlign: 'center' }}></Footer>
      </Layout>
    </Layout>
  );
};

export default Success;
